package com.rodrigoguerrero.myweather.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.myweather.ui.models.uistate.FutureDaysWeatherUiState
import com.rodrigoguerrero.myweather.ui.models.events.FutureWeatherEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.isError
import com.rodrigoguerrero.myweather.ui.models.uistate.isLoading
import com.rodrigoguerrero.myweather.ui.models.uistate.updateDays
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FutureDaysWeatherViewModel : ViewModel() {

    private val _state = MutableStateFlow(FutureDaysWeatherUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: FutureWeatherEvent) {
        when (event) {
            FutureWeatherEvent.ShowError -> _state.isError()
            FutureWeatherEvent.ShowLoading -> _state.isLoading()
            is FutureWeatherEvent.UpdateDays -> _state.updateDays(
                days = event.days,
                timeZoneId = event.timeZoneId,
                currentTime = event.currentTime,
            )
        }
    }
}
