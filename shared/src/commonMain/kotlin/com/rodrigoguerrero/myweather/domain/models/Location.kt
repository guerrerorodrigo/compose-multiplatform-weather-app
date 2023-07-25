package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.LocationDto

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZoneId: String,
    val localTimeEpoch: Long,
    val localTime: String,
)

internal fun LocationDto.toDomain() = Location(
    name = name,
    region = region,
    country = country,
    latitude = latitude,
    longitude = longitude,
    timeZoneId = timeZoneId,
    localTimeEpoch = localTimeEpoch,
    localTime = localTime,
)
