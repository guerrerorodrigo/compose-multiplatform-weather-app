package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource
import com.rodrigoguerrero.myweather.data.local.db.LocationDto

internal class SaveFavoriteLocationInteractorImpl(
    private val dataSource: LocationDataSource,
) : SaveFavoriteLocationInteractor {

    override suspend fun invoke(location: String) {
        dataSource.insertLocation(
            LocationDto(
                id = 0,
                location = location,
                createdAt = 0
            )
        )
    }
}
