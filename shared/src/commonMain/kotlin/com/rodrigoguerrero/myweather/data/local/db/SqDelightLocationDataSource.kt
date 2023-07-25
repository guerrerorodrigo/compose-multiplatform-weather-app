package com.rodrigoguerrero.myweather.data.local.db

import com.rodrigoguerrero.mywheather.database.AppDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.LocationEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqDelightLocationDataSource(database: AppDatabase) : LocationDataSource {

    private val queries = database.locationQueries

    override fun getLocations(): Flow<List<LocationDto>> {
        return queries
                .getLocations()
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity -> async { entity.toLocationDto() } }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertLocation(location: LocationDto) {
        queries.insertLocationEntity(
            id = null,
            location = location.location,
            createdAt = Clock.System.now().toEpochMilliseconds(),
        )
    }

    override suspend fun deleteLocation(id: Long) {
        queries.deleteLocation(id)
    }

    override suspend fun getLocationById(location: String): Flow<List<LocationDto>> {
        return queries
            .getLocationByName(location)
            .asFlow()
            .mapToList()
            .map { entities ->
                supervisorScope {
                    entities
                        .map { entity -> async { entity.toLocationDto() } }
                        .map { it.await() }
                }
            }
    }
}

private fun LocationEntity.toLocationDto() = LocationDto(
    id = id.toInt(),
    location = location,
    createdAt = createdAt,
)
