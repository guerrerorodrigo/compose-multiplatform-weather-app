package com.rodrigoguerrero.myweather.data.local.db

import kotlinx.coroutines.flow.Flow

interface LocationDataSource {
    fun getLocations(): Flow<List<LocationDto>>
    suspend fun insertLocation(location: LocationDto)
    suspend fun deleteLocation(id: Long)
    suspend fun getLocationById(location: String): List<LocationDto>
}