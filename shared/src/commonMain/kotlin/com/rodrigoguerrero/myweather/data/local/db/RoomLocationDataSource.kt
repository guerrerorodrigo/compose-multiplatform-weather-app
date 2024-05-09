package com.rodrigoguerrero.myweather.data.local.db

import com.rodrigoguerrero.myweather.data.local.db.room.AppDatabase
import com.rodrigoguerrero.myweather.data.local.db.room.LocationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class RoomLocationDataSource(
    private val db: AppDatabase,
) : LocationDataSource {

    override fun getLocations(): Flow<List<LocationDto>> =
        db.locationDao().getLocations()
            .map { entities -> entities.map { entity -> entity.toDto() } }
            .flowOn(Dispatchers.IO)

    override suspend fun insertLocation(location: LocationDto) {
        db.locationDao().insertLocation(location.toEntity())
    }

    override suspend fun deleteLocation(id: Long) {
        db.locationDao().deleteLocation(id = id.toInt())
    }

    override fun getLocationById(location: String): Flow<LocationDto?> =
        db.locationDao().getLocationByName(location = location)
            .map { entity -> entity?.toDto() }
            .flowOn(Dispatchers.IO)
}

private fun LocationEntity.toDto() = LocationDto(
    id = id,
    location = location,
    createdAt = createdAt,
)

private fun LocationDto.toEntity() = LocationEntity(
    id = id,
    location = location,
    createdAt = createdAt,
)
