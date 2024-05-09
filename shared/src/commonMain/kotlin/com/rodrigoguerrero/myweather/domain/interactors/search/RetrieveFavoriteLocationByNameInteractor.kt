package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.domain.models.FavoriteLocation

interface RetrieveFavoriteLocationByNameInteractor {
    suspend operator fun invoke(location: String): FavoriteLocation?
}
