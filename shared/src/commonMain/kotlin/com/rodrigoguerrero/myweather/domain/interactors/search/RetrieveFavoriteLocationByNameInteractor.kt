package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.domain.models.FavoriteLocation
import kotlinx.coroutines.flow.Flow

interface RetrieveFavoriteLocationByNameInteractor {
    suspend operator fun invoke(location: String): Flow<List<FavoriteLocation>>
}
