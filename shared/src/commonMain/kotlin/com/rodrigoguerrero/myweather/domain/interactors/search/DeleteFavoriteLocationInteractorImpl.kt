package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource
import com.rodrigoguerrero.myweather.ui.models.uimodels.FavoriteLocation

class DeleteFavoriteLocationInteractorImpl(
    private val dataSource: LocationDataSource,
) : DeleteFavoriteLocationInteractor {
    override suspend fun invoke(favoriteLocation: FavoriteLocation) {
        dataSource.deleteLocation(favoriteLocation.id.toLong())
    }
}
