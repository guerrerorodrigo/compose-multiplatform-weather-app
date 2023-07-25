package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource
import com.rodrigoguerrero.myweather.data.local.db.LocationDto
import com.rodrigoguerrero.myweather.domain.models.FavoriteLocation
import kotlinx.coroutines.flow.map

internal class RetrieveFavoriteLocationsInteractorImpl(
    private val dataSource: LocationDataSource,
) : RetrieveFavoriteLocationsInteractor {

    override suspend fun invoke() = dataSource.getLocations()
        .map { locations -> locations.map { it.toDomain() } }
}

internal fun LocationDto.toDomain() = FavoriteLocation(
    location = location,
    id = id,
)
