package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    suspend operator fun invoke(
        query: String,
    ): Flow<ResourceResult<List<SearchResult>>>
}
