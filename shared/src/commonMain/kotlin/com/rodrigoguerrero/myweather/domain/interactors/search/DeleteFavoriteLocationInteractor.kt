package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.ui.models.uimodels.FavoriteLocation

interface DeleteFavoriteLocationInteractor {
    suspend operator fun invoke(favoriteLocation: FavoriteLocation)
}
