package com.rodrigoguerrero.myweather.ui.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.myweather.data.local.datastore.PreferencesRepository
import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationByNameInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.SaveFavoriteLocationInteractor
import com.rodrigoguerrero.myweather.domain.interactors.weather.ForecastInteractor
import com.rodrigoguerrero.myweather.domain.location.LocationService
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.ui.models.PermissionsState
import com.rodrigoguerrero.myweather.ui.models.events.MainEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.MainUiState
import com.rodrigoguerrero.myweather.ui.models.uistate.isError
import com.rodrigoguerrero.myweather.ui.models.uistate.isLoading
import com.rodrigoguerrero.myweather.ui.models.uistate.updateForecast
import com.rodrigoguerrero.myweather.ui.models.uistate.updateQuery
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel : ViewModel(), KoinComponent {

    private val preferencesRepository: PreferencesRepository = get()
    private val forecastInteractor: ForecastInteractor = get()
    private val saveFavoriteLocationInteractor: SaveFavoriteLocationInteractor = get()
    private val favoriteLocationByIdInteractor: RetrieveFavoriteLocationByNameInteractor = get()
    private val permissionsController: PermissionsController = get()
    private val locationService: LocationService = get()

    private val permissions = MutableStateFlow(PermissionsState())
    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    private val locationPermissionsFlow = preferencesRepository.location
        .combine(permissions) { location, permissions ->
            when {
                location.isEmpty() && (permissions.isDenied == true || permissions.isPermanentlyDenied == true) -> {
                    MainEvent.ShowEmptyMessage
                }
                location.isEmpty() && permissions.isGranted == true -> {
                    MainEvent.LoadForecastWithLocation(location)
                }
                else -> MainEvent.UpdateQuery(query = location)
            }
        }

    init {
        viewModelScope.launch {
            locationPermissionsFlow.collectLatest { event -> onEvent(event) }
        }

        viewModelScope.launch {
            when (permissionsController.getPermissionState(Permission.LOCATION)) {
                PermissionState.NotDetermined -> onEvent(MainEvent.RequestLocationPermission)
                PermissionState.Granted -> permissions.update { it.copy(isGranted = true) }
                else -> {}
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.LoadForecast -> loadForecast(_state.value.query)
            is MainEvent.UpdateQuery -> {
                _state.updateQuery(event.query)
                shouldShowSnackbar(event.query)
                loadForecast(_state.value.query)
            }

            MainEvent.Loading -> _state.isLoading()
            MainEvent.Error -> _state.isError()
            is MainEvent.ShowSaveLocationSnackbar -> _state.update { it.copy(locationToSave = event.location) }
            is MainEvent.SaveLocation -> saveLocation(event.location)
            MainEvent.ShowEmptyMessage -> _state.update {
                it.copy(
                    isLoading = false,
                    showEmptyMessage = true,
                )
            }

            MainEvent.RequestLocationPermission -> requestPermission()
            is MainEvent.LoadForecastWithLocation -> loadForecastWithLocation()
        }
    }

    @Composable
    fun BindPermissionController() {
        BindEffect(permissionsController)
    }

    private fun shouldShowSnackbar(location: String) {
        viewModelScope.launch {
            val favoriteLocation = favoriteLocationByIdInteractor(location)
            if (favoriteLocation.isEmpty()) {
                onEvent(MainEvent.ShowSaveLocationSnackbar(location))
            }
        }
    }
    private fun loadForecastWithLocation() {
        viewModelScope.launch {
            val location = locationService.getCurrentLocation()
            loadForecast(location.toString())
        }
    }

    private fun requestPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)
                permissions.update { it.copy(isGranted = true) }
            } catch (deniedAlwaysException: DeniedAlwaysException) {
                permissions.update { it.copy(isPermanentlyDenied = true) }
            } catch (deniedException: DeniedException) {
                permissions.update { it.copy(isDenied = true) }
            }
        }
    }

    private fun saveLocation(location: String) {
        viewModelScope.launch {
            saveFavoriteLocationInteractor(location)
        }
    }

    private fun loadForecast(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                forecastInteractor(
                    query = query,
                    airQuality = AirQuality.YES,
                    days = 3,
                    weatherAlerts = WeatherAlerts.YES,
                ).collectLatest { result ->
                    when (result) {
                        is ResourceResult.Error -> onEvent(MainEvent.Error)
                        ResourceResult.Loading -> onEvent(MainEvent.Loading)
                        is ResourceResult.Success -> _state.updateForecast(result.data)
                    }
                }
            }
        }
    }
}
