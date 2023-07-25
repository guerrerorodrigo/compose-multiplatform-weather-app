package com.rodrigoguerrero.myweather.data.remote.models.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourDto(
    @SerialName("time_epoch") val timeEpoch: Long,
    @SerialName("time") val time: String,
    @SerialName("temp_c") val temperatureInCelsius: Double,
    @SerialName("temp_f") val temperatureInFahrenheit: Double,
    @SerialName("is_day") val isDay: Int,
    @SerialName("condition") val condition: ConditionDto,
    @SerialName("wind_mph") val windSpeedMph: Double,
    @SerialName("wind_kph") val windSpeedKph: Double,
    @SerialName("wind_degree") val windDirectionDegrees: Int,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("pressure_mb") val pressureMb: Double,
    @SerialName("pressure_in") val pressureInches: Double,
    @SerialName("precip_mm") val precipitationMm: Double,
    @SerialName("precip_in") val precipitationInches: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("cloud") val cloudCoverage: Int,
    @SerialName("feelslike_c") val feelsLikeTemperatureInCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeTemperatureInFahrenheit: Double,
    @SerialName("windchill_c") val windchillTemperatureInCelsius: Double,
    @SerialName("windchill_f") val windchillTemperatureInFahrenheit: Double,
    @SerialName("heatindex_c") val heatIndexCelsius: Double,
    @SerialName("heatindex_f") val heatIndexFahrenheit: Double,
    @SerialName("dewpoint_c") val dewPointCelsius: Double,
    @SerialName("dewpoint_f") val dewPointFahrenheit: Double,
    @SerialName("will_it_rain") val willItRain: Int,
    @SerialName("chance_of_rain") val chanceOfRain: Int,
    @SerialName("will_it_snow") val willItSnow: Int,
    @SerialName("chance_of_snow") val chanceOfSnow: Int,
    @SerialName("vis_km") val visibilityKm: Double,
    @SerialName("vis_miles") val visibilityMiles: Double,
    @SerialName("gust_mph") val windGustMph: Double,
    @SerialName("gust_kph") val windGustKph: Double,
    @SerialName("uv") val uvIndex: Double,
    @SerialName("air_quality") val airQuality: AirQualityDto,
)
