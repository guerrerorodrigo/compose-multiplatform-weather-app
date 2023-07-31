package com.rodrigoguerrero.myweather.ui.viewmodels

import com.rodrigoguerrero.myweather.ui.models.events.TomorrowWeatherEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.TomorrowWeatherUiState
import com.rodrigoguerrero.myweather.ui.models.uistate.setResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TomorrowWeatherViewModel : ViewModel() {

    private val _state = MutableStateFlow(TomorrowWeatherUiState())
    val state: StateFlow<TomorrowWeatherUiState> = _state.asStateFlow()

    fun onEvent(event: TomorrowWeatherEvent) {
        when (event) {
            TomorrowWeatherEvent.ShowError -> _state.update { TomorrowWeatherUiState(isError = true) }
            TomorrowWeatherEvent.ShowLoading -> _state.update { TomorrowWeatherUiState(isLoading = true) }
            is TomorrowWeatherEvent.UpdateForecast -> _state.setResponse(event.forecast)
        }
    }
}