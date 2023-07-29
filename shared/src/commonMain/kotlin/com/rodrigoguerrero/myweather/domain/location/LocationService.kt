package com.rodrigoguerrero.myweather.domain.location

interface LocationService {
    suspend fun getCurrentLocation(): DeviceLocation
}
