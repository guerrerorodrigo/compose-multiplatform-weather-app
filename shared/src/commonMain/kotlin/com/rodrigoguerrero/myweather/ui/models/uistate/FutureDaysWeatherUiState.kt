package com.rodrigoguerrero.myweather.ui.models.uistate

import com.rodrigoguerrero.myweather.common.formatTimeWithDayName
import com.rodrigoguerrero.myweather.common.isToday
import com.rodrigoguerrero.myweather.common.parseTime
import com.rodrigoguerrero.myweather.domain.models.ForecastDay
import com.rodrigoguerrero.myweather.ui.models.uimodels.DayUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class FutureDaysWeatherUiState(
    val days: List<DayUi> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

internal fun MutableStateFlow<FutureDaysWeatherUiState>.isLoading() {
    update { it.copy(isLoading = true, isError = false) }
}

internal fun MutableStateFlow<FutureDaysWeatherUiState>.isError() {
    update {
        it.copy(
            isError = true,
            isLoading = false,
        )
    }
}

internal fun MutableStateFlow<FutureDaysWeatherUiState>.updateDays(
    days: List<ForecastDay>,
    timeZoneId: String,
    currentTime: Long,
) {
    update {
        it.copy(
            isError = false,
            isLoading = false,
            days = days.map { day -> day.toUi(timeZoneId, currentTime) }
        )
    }
}

private fun ForecastDay.toUi(timeZoneId: String, currentTime: Long) = DayUi(
    name = if (
        isToday(
            localTimeEpoch = dateEpoch,
            currentTimeEpoch = currentTime,
            localTimeEpochTimeZoneId = "GMT",
            currentTimeEpochTimeZoneId = timeZoneId,
        )
    ) {
        null
    } else {
        formatTimeWithDayName(
            currentTime = parseTime(
                localTimeEpoch = dateEpoch,
                timeZoneId = timeZoneId,
            )
        )
    },
    forecast = day.condition.condition,
    iconUrl = "https:${day.condition.iconUrl}",
    maxTemp = day.maxTemperatureInCelsius.toInt().toString(),
    minTemp = day.minTemperatureInCelsius.toInt().toString(),
)
