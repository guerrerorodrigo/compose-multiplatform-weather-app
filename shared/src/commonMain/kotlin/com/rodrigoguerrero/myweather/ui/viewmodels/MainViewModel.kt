package com.rodrigoguerrero.myweather.ui.viewmodels

import com.rodrigoguerrero.myweather.data.local.datastore.PreferencesRepository
import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationByNameInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.SaveFavoriteLocationInteractor
import com.rodrigoguerrero.myweather.domain.interactors.weather.ForecastInteractor
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.ui.models.events.MainEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.MainUiState
import com.rodrigoguerrero.myweather.ui.models.uistate.isError
import com.rodrigoguerrero.myweather.ui.models.uistate.isLoading
import com.rodrigoguerrero.myweather.ui.models.uistate.updateForecast
import com.rodrigoguerrero.myweather.ui.models.uistate.updateQuery
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel : ViewModel(), KoinComponent {

    private val preferencesRepository: PreferencesRepository = get()
    private val forecastInteractor: ForecastInteractor = get()
    private val saveFavoriteLocationInteractor: SaveFavoriteLocationInteractor = get()
    private val favoriteLocationByIdInteractor: RetrieveFavoriteLocationByNameInteractor = get()

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.location
                .collectLatest { location ->
                    if (location.isEmpty()) {
                        onEvent(MainEvent.ShowEmptyMessage)
                    } else {
                        onEvent(MainEvent.UpdateQuery(query = location))
                        val favoriteLocation = favoriteLocationByIdInteractor(location)
                        if (favoriteLocation.isEmpty()) {
                            onEvent(MainEvent.ShowSaveLocationSnackbar(location))
                        }
                    }
                }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.LoadForecast -> loadForecast()
            is MainEvent.UpdateQuery -> {
                _state.updateQuery(event.query)
                loadForecast()
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
        }
    }

    private fun saveLocation(location: String) {
        viewModelScope.launch {
            saveFavoriteLocationInteractor(location)
        }
    }

    private fun loadForecast() {
        if (_state.value.query.isNotEmpty()) {
            viewModelScope.launch {
                forecastInteractor(
                    query = _state.value.query,
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
