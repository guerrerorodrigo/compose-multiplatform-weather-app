package com.rodrigoguerrero.myweather.ui.models.events

import com.rodrigoguerrero.myweather.domain.models.Forecast

sealed class TodayWeatherEvent {
    data class UpdateQuery(val query: String) : TodayWeatherEvent()
    object ShowError : TodayWeatherEvent()
    object ShowLoading : TodayWeatherEvent()
    object ShowEmptyLocation : TodayWeatherEvent()
    data class UpdateForecast(val forecast: Forecast?) : TodayWeatherEvent()
}
