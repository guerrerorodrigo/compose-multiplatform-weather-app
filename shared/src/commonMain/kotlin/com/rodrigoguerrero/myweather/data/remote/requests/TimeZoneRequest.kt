package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/timezone.json")
data class TimeZoneRequest(
    @SerialName("q") val query: String,
)
