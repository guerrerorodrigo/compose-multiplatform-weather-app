@file:OptIn(ExperimentalResourceApi::class)

package com.rodrigoguerrero.myweather.ui.models.uistate

import com.rodrigoguerrero.myweather.common.formatTimeWithDayName
import com.rodrigoguerrero.myweather.common.isSameDay
import com.rodrigoguerrero.myweather.common.parseTime
import com.rodrigoguerrero.myweather.domain.models.Forecast
import com.rodrigoguerrero.myweather.domain.models.ForecastDay
import com.rodrigoguerrero.myweather.domain.models.Hour
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi
import com.rodrigoguerrero.myweather.ui.models.uimodels.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.wind_speed_light
import myweather.shared.generated.resources.wind_speed_moderate
import myweather.shared.generated.resources.wind_speed_strong
import myweather.shared.generated.resources.wind_speed_super_strong
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

data class TomorrowWeatherUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val date: String = "",
    val maxTemperature: String = "",
    val minTemperature: String = "",
    val condition: String = "",
    val conditionIconUrl: String = "",
    val hourlyForecasts: List<HourUi> = emptyList(),
    val rainChance: String = "",
    val humidity: String = "",
    val uvIndex: String = "",
    val sunsetSunrise: String = "",
    val totalDailyVolumeRain: String = "",
    val windForce: StringResource? = null,
    val maxWindSpeed: Int = 0,
)

internal fun MutableStateFlow<TomorrowWeatherUiState>.isLoading() {
    update { TomorrowWeatherUiState() }
}

internal fun MutableStateFlow<TomorrowWeatherUiState>.isError() {
    update {
        it.copy(
            isError = true,
            isLoading = false,
        )
    }
}

internal fun MutableStateFlow<TomorrowWeatherUiState>.setResponse(result: Forecast) {
    val allDaysHours = getTomorrowDayHours(
        forecastDays = result.forecastDays,
        currentTimeEpoch = result.location.localTimeEpoch,
        timeZoneId = result.location.timeZoneId,

        ).map { hour -> hour.toUi() }.subList(0, 24)
    update {
        val tomorrowForecast = result.forecastDays[1]
        it.copy(
            isError = false,
            isLoading = false,
            date = formatTimeWithDayName(
                currentTime = parseTime(
                    localTimeEpoch = tomorrowForecast.dateEpoch,
                    timeZoneId = result.location.timeZoneId,
                )
            ),
            maxTemperature = tomorrowForecast.day.maxTemperatureInCelsius.toInt().toString(),
            minTemperature = tomorrowForecast.day.minTemperatureInCelsius.toInt().toString(),
            condition = tomorrowForecast.day.condition.condition,
            conditionIconUrl = "https:${tomorrowForecast.day.condition.iconUrl}",
            hourlyForecasts = allDaysHours,
            rainChance = tomorrowForecast.day.chanceOfRain.toString(),
            humidity = tomorrowForecast.day.humidity.toInt().toString(),
            uvIndex = tomorrowForecast.day.uvIndex.toInt().toString(), // TODO: Add moderate, high, low, etc.
            sunsetSunrise = with(tomorrowForecast.astronomy) {
                "${sunriseTime.lowercase()}, ${sunsetTime.lowercase()}"
            },
            totalDailyVolumeRain = tomorrowForecast.day.totalPrecipitationMm.toString(),
            windForce = getWindForce(tomorrowForecast.day.maxWindSpeedKph.toInt()),
            maxWindSpeed = tomorrowForecast.day.maxWindSpeedKph.toInt(),
        )
    }
}

private fun getWindForce(windSpeed: Int) = when (windSpeed) {
    in 0..20 -> Res.string.wind_speed_light
    in 21..40 -> Res.string.wind_speed_moderate
    in 41..60 -> Res.string.wind_speed_strong
    else -> Res.string.wind_speed_super_strong
}

private fun getTomorrowDayHours(
    forecastDays: List<ForecastDay>,
    timeZoneId: String,
    currentTimeEpoch: Long,
): List<Hour> {
    val hours = mutableListOf<Hour>()
    forecastDays.forEach { day ->
        hours.addAll(day.hours)
    }
    return hours
        .filter { hour ->
            val isSameDay = isSameDay(
                currentTimeEpoch = currentTimeEpoch,
                localTimeEpoch = hour.timeEpoch,
                timeZoneId = timeZoneId,
            )
            !isSameDay
        }
}
