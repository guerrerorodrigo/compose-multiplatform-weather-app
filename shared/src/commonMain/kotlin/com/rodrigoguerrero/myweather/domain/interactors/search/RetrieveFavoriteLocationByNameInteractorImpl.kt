package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource

internal class RetrieveFavoriteLocationByNameInteractorImpl(
    private val dataSource: LocationDataSource,
) : RetrieveFavoriteLocationByNameInteractor {

    override suspend fun invoke(location: String) =
        dataSource.getLocationById(location).map { it.toDomain() }
}
