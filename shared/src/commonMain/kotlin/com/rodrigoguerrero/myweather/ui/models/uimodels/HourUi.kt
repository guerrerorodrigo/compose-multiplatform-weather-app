package com.rodrigoguerrero.myweather.ui.models.uimodels

import com.rodrigoguerrero.myweather.domain.models.Hour

data class HourUi(
    val hour: String,
    val iconUrl: String,
    val temperature: String,
    val chanceOfRain: String,
    val precipitationMmPercentage: Double,
    val dewPoint: String,
    val precipitationMm: String,
    val windSpeed: Int,
    val windDirection: String,
    val windDirectionDegrees: Int,
)

internal fun Hour.toUi() = HourUi(
    hour = time.split(" ")[1],
    iconUrl = "https:${condition.iconUrl}",
    temperature = temperatureInCelsius.toInt().toString(),
    chanceOfRain = chanceOfRain.toString(),
    dewPoint = dewPointCelsius.toInt().toString(),
    precipitationMm = precipitationMm.toString(),
    precipitationMmPercentage = (precipitationMm / 3).coerceAtMost(1.0),
    windSpeed = windSpeedKph.toInt(),
    windDirection = windDirection,
    windDirectionDegrees = windDirectionDegrees + 180,
)
