package com.rodrigoguerrero.myweather.domain.interactors.search

interface SaveFavoriteLocationInteractor {
    suspend operator fun invoke(location: String)
}
