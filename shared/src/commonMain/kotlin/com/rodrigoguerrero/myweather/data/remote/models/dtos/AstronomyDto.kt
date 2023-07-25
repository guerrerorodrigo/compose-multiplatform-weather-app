package com.rodrigoguerrero.myweather.data.remote.models.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AstronomyDto(
    @SerialName("sunrise") val sunriseTime: String,
    @SerialName("sunset") val sunsetTime: String,
    @SerialName("moonrise") val moonriseTime: String,
    @SerialName("moonset") val moonsetTime: String,
    @SerialName("moon_phase") val moonPhase: String,
    @SerialName("moon_illumination") val moonIllumination: String,
    @SerialName("is_moon_up") val isMoonUp: Int,
    @SerialName("is_sun_up") val isSunUp: Int,
)
