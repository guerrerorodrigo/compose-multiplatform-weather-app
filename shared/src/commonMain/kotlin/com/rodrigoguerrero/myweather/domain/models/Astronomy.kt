package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.AstronomyDto

data class Astronomy(
    val sunriseTime: String,
    val sunsetTime: String,
    val moonriseTime: String,
    val moonsetTime: String,
    val moonPhase: String,
    val moonIllumination: Int,
)

internal fun AstronomyDto.toDomain() = Astronomy(
    sunriseTime = sunriseTime,
    sunsetTime = sunsetTime,
    moonriseTime = moonriseTime,
    moonsetTime = moonsetTime,
    moonPhase = moonPhase,
    moonIllumination = moonIllumination,
)
