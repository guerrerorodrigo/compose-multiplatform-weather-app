package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/history.json")
data class HistoryRequest(
    @SerialName("q") val query: String,
    @SerialName("dt") val date: String,
)
