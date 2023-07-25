package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/forecast.json")
data class ForecastRequest(
    @SerialName("q") val query: String,
    @SerialName("aqi") val airQuality: String = "no",
    @SerialName("days") val days: Int = 3,
    @SerialName("alerts") val alerts: String = "yes",
)
