package com.rodrigoguerrero.myweather.data.remote.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName

@Resource("/v1/search.json")
data class SearchRequest(
    @SerialName("q") val query: String,
)
