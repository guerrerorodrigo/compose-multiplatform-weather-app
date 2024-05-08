@file:OptIn(ExperimentalResourceApi::class)

package com.rodrigoguerrero.myweather.ui.models.uistate

import androidx.compose.ui.graphics.Color
import com.rodrigoguerrero.myweather.common.formatTime
import com.rodrigoguerrero.myweather.common.getHour
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
import myweather.shared.generated.resources.wind_direction_east
import myweather.shared.generated.resources.wind_direction_north
import myweather.shared.generated.resources.wind_direction_northeast
import myweather.shared.generated.resources.wind_direction_northwest
import myweather.shared.generated.resources.wind_direction_south
import myweather.shared.generated.resources.wind_direction_southeast
import myweather.shared.generated.resources.wind_direction_southwest
import myweather.shared.generated.resources.wind_direction_west
import myweather.shared.generated.resources.wind_speed_light
import myweather.shared.generated.resources.wind_speed_moderate
import myweather.shared.generated.resources.wind_speed_strong
import myweather.shared.generated.resources.wind_speed_super_strong
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

data class TodayWeatherUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val currentTime: String = "",
    val currentTemperature: String = "",
    val feelsLikeTemperature: String = "",
    val condition: String = "",
    val conditionIconUrl: String = "",
    val hourlyForecasts: List<HourUi> = emptyList(),
    val rainChance: String = "",
    val humidity: String = "",
    val dewPoint: String = "",
    val pressure: String = "",
    val uvIndex: String = "",
    val visibility: String = "",
    val totalPrecipitation: String = "",
    val windSpeed: Int = 0,
    val windDirectionDegrees: Int = 0,
    val windDirection: StringResource? = null,
    val sunrise: String = "",
    val sunset: String = "",
    val moonrise: String = "",
    val moonset: String = "",
    val query: String = "",
) {
    val windSpeedColor: Color
        get() {
            return getWindColor(windSpeed)
        }

    val windSpeedDescription: StringResource
        get() {
            return when (windSpeed) {
                in 0..20 -> Res.string.wind_speed_light
                in 21..40 -> Res.string.wind_speed_moderate
                in 41..60 -> Res.string.wind_speed_strong
                else -> Res.string.wind_speed_super_strong
            }
        }
}

internal fun getWindColor(speed: Int) = when (speed) {
    in 0..20 -> Color.Cyan
    in 21..40 -> Color.Green
    in 41..60 -> Color.Yellow
    else -> Color.Red
}
internal fun MutableStateFlow<TodayWeatherUiState>.isLoading() {
    update { TodayWeatherUiState() }
}

internal fun MutableStateFlow<TodayWeatherUiState>.isError() {
    update {
        it.copy(
            isError = true,
            isLoading = false,
        )
    }
}

internal fun MutableStateFlow<TodayWeatherUiState>.updateQuery(query: String) {
    update { it.copy(query = query) }
}

internal fun MutableStateFlow<TodayWeatherUiState>.setResponse(result: Forecast) {
    val allDaysHours = getTomorrowDayHours(
        forecastDays = result.forecastDays,
        currentTimeEpoch = result.location.localTimeEpoch,
        timeZoneId = result.location.timeZoneId,

        ).map { hour -> hour.toUi() }.subList(0, 24)
    update {
        it.copy(
            isError = false,
            isLoading = false,
            currentTemperature = result.currentWeather.temperatureCelsius.toInt().toString(),
            currentTime = formatTime(
                currentTime = parseTime(
                    localTimeEpoch = result.location.localTimeEpoch,
                    timeZoneId = result.location.timeZoneId,
                )
            ),
            feelsLikeTemperature = result.currentWeather.feelsLikeTemperatureCelsius.toInt()
                .toString(),
            condition = result.currentWeather.weatherCondition.condition,
            conditionIconUrl = "https:${result.currentWeather.weatherCondition.iconUrl}",
            hourlyForecasts = allDaysHours,
            rainChance = result.forecastDays.first().day.chanceOfRain.toString(),
            humidity = result.currentWeather.humidityPercentage.toInt().toString(),
            dewPoint = allDaysHours.first().dewPoint,
            pressure = result.currentWeather.pressureMillibars.toInt().toString(),
            uvIndex = result.currentWeather.uvIndex.toInt()
                .toString(), // TODO: Add moderate, high, low, etc.
            visibility = result.currentWeather.visibilityKm.toInt().toString(),
            totalPrecipitation = result.forecastDays.first().day.totalPrecipitationMm.toString(),
            windSpeed = allDaysHours.first().windSpeed,
            windDirectionDegrees = allDaysHours.first().windDirectionDegrees,
            windDirection = getWindDirection(allDaysHours.first().windDirection),
            sunrise = result.forecastDays.first().astronomy.sunriseTime.lowercase(),
            sunset = result.forecastDays.first().astronomy.sunsetTime.lowercase(),
            moonrise = result.forecastDays.first().astronomy.moonriseTime.lowercase(),
            moonset = result.forecastDays.first().astronomy.moonsetTime.lowercase(),
        )
    }
}

private fun getWindDirection(windDirection: String): StringResource =
    when (windDirection.lowercase()) {
        "n" -> Res.string.wind_direction_north
        "nne", "ne", "ene" -> Res.string.wind_direction_northeast
        "e" -> Res.string.wind_direction_east
        "ese", "se", "sse" -> Res.string.wind_direction_southeast
        "s" -> Res.string.wind_direction_south
        "ssw", "sw", "wsw" -> Res.string.wind_direction_southwest
        "w" -> Res.string.wind_direction_west
        "wnw", "nw", "nnw" -> Res.string.wind_direction_northwest
        else -> throw IllegalArgumentException("Invalid wind direction")
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
            isSameDay && getHour(hour.timeEpoch, timeZoneId) >= getHour(
                currentTimeEpoch,
                timeZoneId
            ) || !isSameDay
        }
}
