package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.SearchResultDto

data class SearchResult(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
)

internal fun SearchResultDto.toDomain() = SearchResult(
    id = id,
    name = name,
    region = region,
    country = country,
    latitude = latitude,
    longitude = longitude,
)
