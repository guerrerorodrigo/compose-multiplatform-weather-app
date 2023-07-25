package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/current.json")
data class CurrentWeatherRequest(
    @SerialName("q") val query: String,
    @SerialName("aqi") val airQuality: String = "no",
)
