package com.rodrigoguerrero.myweather.data.remote.models.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("last_updated_epoch") val lastUpdatedDateEpoch: Long,
    @SerialName("last_updated") val lastUpdatedDate: String,
    @SerialName("temp_c") val temperatureCelsius: Double,
    @SerialName("temp_f") val temperatureFahrenheit: Double,
    @SerialName("is_day") val isDay: Int,
    @SerialName("condition") val weatherCondition: ConditionDto,
    @SerialName("wind_mph") val windSpeedMph: Double,
    @SerialName("wind_kph") val windSpeedKph: Double,
    @SerialName("wind_degree") val windDirectionDegrees: Double,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("pressure_mb") val pressureMillibars: Double,
    @SerialName("pressure_in") val pressureInches: Double,
    @SerialName("precip_mm") val precipitationAmountMillimeters: Double,
    @SerialName("precip_in") val precipitationAmountInches: Double,
    @SerialName("humidity") val humidityPercentage: Double,
    @SerialName("cloud") val cloudCoverPercentage: Double,
    @SerialName("feelslike_c") val feelsLikeTemperatureCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeTemperatureFahrenheit: Double,
    @SerialName("vis_km") val visibilityKm: Double,
    @SerialName("vis_miles") val visibilityMiles: Double,
    @SerialName("uv") val uvIndex: Double,
    @SerialName("gust_mph") val windGustMph: Double,
    @SerialName("gust_kph") val windGustKph: Double,
    @SerialName("air_quality") val airQuality: AirQualityDto,
)
