package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.WeatherDto

data class Weather(
    val lastUpdatedDateEpoch: Long,
    val lastUpdatedDate: String,
    val temperatureCelsius: Double,
    val temperatureFahrenheit: Double,
    val isDay: TimeOfDay,
    val weatherCondition: Condition,
    val windSpeedMph: Double,
    val windSpeedKph: Double,
    val windDirectionDegrees: Double,
    val windDirection: String,
    val pressureMillibars: Double,
    val pressureInches: Double,
    val precipitationAmountMillimeters: Double,
    val precipitationAmountInches: Double,
    val humidityPercentage: Double,
    val cloudCoverPercentage: Double,
    val feelsLikeTemperatureCelsius: Double,
    val feelsLikeTemperatureFahrenheit: Double,
    val visibilityKm: Double,
    val visibilityMiles: Double,
    val uvIndex: Double,
    val windGustMph: Double,
    val windGustKph: Double,
    val airQuality: AirQuality?,
)

internal fun WeatherDto.toDomain() = Weather(
    lastUpdatedDateEpoch = lastUpdatedDateEpoch,
    lastUpdatedDate = lastUpdatedDate,
    temperatureCelsius = temperatureCelsius,
    temperatureFahrenheit = temperatureFahrenheit,
    isDay = if (isDay == 1) TimeOfDay.Day else TimeOfDay.Night,
    weatherCondition = weatherCondition.toDomain(),
    windSpeedMph = windSpeedMph,
    windSpeedKph = windSpeedKph,
    windDirectionDegrees = windDirectionDegrees,
    windDirection = windDirection,
    pressureMillibars = pressureMillibars,
    pressureInches = pressureInches,
    precipitationAmountMillimeters = precipitationAmountMillimeters,
    precipitationAmountInches = precipitationAmountInches,
    humidityPercentage = humidityPercentage,
    cloudCoverPercentage = cloudCoverPercentage,
    feelsLikeTemperatureCelsius = feelsLikeTemperatureCelsius,
    feelsLikeTemperatureFahrenheit = feelsLikeTemperatureFahrenheit,
    visibilityKm = visibilityKm,
    visibilityMiles = visibilityMiles,
    uvIndex = uvIndex,
    windGustMph = windGustMph,
    windGustKph = windGustKph,
    airQuality = airQuality?.toDomain(),
)
