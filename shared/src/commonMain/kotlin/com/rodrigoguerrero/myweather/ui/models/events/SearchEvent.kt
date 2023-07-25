package com.rodrigoguerrero.myweather.ui.models.events

import com.rodrigoguerrero.myweather.domain.models.SearchResult
import com.rodrigoguerrero.myweather.ui.models.uimodels.FavoriteLocation
import com.rodrigoguerrero.myweather.ui.models.uimodels.SearchSuggestion

sealed interface SearchEvent {
    data class QueryUpdated(val query: String) : SearchEvent
    object ShowError : SearchEvent
    object ShowLoading : SearchEvent
    data class ShowSuggestions(val suggestions: List<SearchResult>) : SearchEvent
    data class SuggestionSelected(val suggestion: SearchSuggestion) : SearchEvent
    object OnCompleted : SearchEvent
    data class FavoriteLocationsRetrieved(val favoriteLocations: List<FavoriteLocation>) :
        SearchEvent
    data class OnFavoriteSelected(val location: FavoriteLocation) : SearchEvent
    object OnManageFavoriteLocations : SearchEvent
    data class RemoveFavoriteLocation(val location: FavoriteLocation) : SearchEvent
    object ClearQuery : SearchEvent
}
