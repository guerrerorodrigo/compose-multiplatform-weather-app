package com.rodrigoguerrero.myweather.ui.models.uistate

import com.rodrigoguerrero.myweather.domain.models.SearchResult
import com.rodrigoguerrero.myweather.ui.models.uimodels.FavoriteLocation
import com.rodrigoguerrero.myweather.ui.models.uimodels.SearchSuggestion

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isCompleted: Boolean = false,
    val showRemoveFavoritesIcon: Boolean = false,
    val searchSuggestions: List<SearchSuggestion> = emptyList(),
    val favoriteLocations: List<FavoriteLocation> = emptyList(),
)

internal fun SearchResult.toUi() = SearchSuggestion(
    country = country,
    name = name,
    id = id,
    region = region,
)
