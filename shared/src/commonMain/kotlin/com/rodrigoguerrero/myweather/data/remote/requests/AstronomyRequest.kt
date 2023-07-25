package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/astronomy.json")
data class AstronomyRequest(
    @SerialName("q") val query: String,
    @SerialName("dt") val date: String,
)
