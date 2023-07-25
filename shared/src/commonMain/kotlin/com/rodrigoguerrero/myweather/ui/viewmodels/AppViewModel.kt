package com.rodrigoguerrero.myweather.ui.viewmodels

import androidx.compose.runtime.Composable
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationByNameInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.SaveFavoriteLocationInteractor
import com.rodrigoguerrero.myweather.ui.models.events.AppEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.AppUiState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AppViewModel : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(AppUiState())
    val state: StateFlow<AppUiState> = _state.asStateFlow()

    private val interactor: SaveFavoriteLocationInteractor = get()
    private val favoriteLocationByIdInteractor: RetrieveFavoriteLocationByNameInteractor = get()
    private val permissionsController: PermissionsController = get()

    init {
        viewModelScope.launch {
            when (permissionsController.getPermissionState(Permission.LOCATION)) {
                PermissionState.NotDetermined -> onEvent(AppEvent.RequestLocationPermission)
                PermissionState.Granted -> onEvent(AppEvent.OnPermissionGranted)
                else -> {}
            }
        }
    }

    fun onEvent(appEvent: AppEvent) {
        when (appEvent) {
            AppEvent.OnPermissionPermanentlyDenied,
            AppEvent.OnPermissionDenied -> _state.update { it.copy(isLocationPermissionDenied = true) }

            AppEvent.OnPermissionGranted -> {

            }

            AppEvent.RequestLocationPermission -> requestPermission()
            is AppEvent.SaveLocation -> saveLocation(appEvent.location)
        }
    }

    @Composable
    fun BindPermissionController() {
        BindEffect(permissionsController)
    }

    private fun saveLocation(location: String) {
        viewModelScope.launch {
            interactor(location)
        }
    }

    private fun requestPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)
                onEvent(AppEvent.OnPermissionGranted)
            } catch (deniedAlwaysException: DeniedAlwaysException) {
                onEvent(AppEvent.OnPermissionPermanentlyDenied)
            } catch (deniedException: DeniedException) {
                onEvent(AppEvent.OnPermissionDenied)
            }
        }
    }

    fun isLocationSaved(location: String) {
        viewModelScope.launch {
            favoriteLocationByIdInteractor(location)
                .collectLatest { locations ->
                    _state.update { it.copy(isLocationSaved = locations.isNotEmpty()) }
                }
        }
    }
}
