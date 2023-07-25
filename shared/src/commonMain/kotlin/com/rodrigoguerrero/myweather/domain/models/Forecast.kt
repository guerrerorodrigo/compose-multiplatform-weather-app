package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.ForecastResponseDto

data class Forecast(
    val location: Location,
    val currentWeather: Weather,
    val forecastDays: List<ForecastDay>,
)

internal fun ForecastResponseDto.toDomain() = Forecast(
    location = location.toDomain(),
    currentWeather = currentWeather.toDomain(),
    forecastDays = forecast.forecastDays.map { day -> day.toDomain() },
)
