package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.CurrentWeatherResponseDto

data class CurrentWeather(
    val location: Location,
    val currentWeather: Weather,
)

internal fun CurrentWeatherResponseDto.toDomain() = CurrentWeather(
    location = location.toDomain(),
    currentWeather = currentWeather.toDomain(),
)
