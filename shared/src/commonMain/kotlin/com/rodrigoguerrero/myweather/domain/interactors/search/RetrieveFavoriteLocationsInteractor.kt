package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.domain.models.FavoriteLocation
import kotlinx.coroutines.flow.Flow

interface RetrieveFavoriteLocationsInteractor {
    suspend operator fun invoke(): Flow<List<FavoriteLocation>>
}
