package com.rodrigoguerrero.myweather.ui.models.events

import com.rodrigoguerrero.myweather.domain.models.ForecastDay

sealed class FutureWeatherEvent {
    object ShowError : FutureWeatherEvent()
    object ShowLoading : FutureWeatherEvent()
    data class UpdateDays(
        val days: List<ForecastDay>,
        val timeZoneId: String,
        val currentTime: Long,
    ) : FutureWeatherEvent()
}
