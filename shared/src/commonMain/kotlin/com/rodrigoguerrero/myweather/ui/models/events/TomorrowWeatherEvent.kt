package com.rodrigoguerrero.myweather.ui.models.events

import com.rodrigoguerrero.myweather.domain.models.Forecast

sealed interface TomorrowWeatherEvent {
    data class UpdateForecast(val forecast: Forecast) : TomorrowWeatherEvent
    object ShowError : TomorrowWeatherEvent
    object ShowLoading : TomorrowWeatherEvent
}
