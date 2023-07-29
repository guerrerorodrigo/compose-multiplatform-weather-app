package com.rodrigoguerrero.myweather.ui.models.events

sealed interface MainEvent {
    data class UpdateQuery(val query: String) : MainEvent
    object LoadForecast : MainEvent
    object Loading : MainEvent
    object Error : MainEvent
    data class ShowSaveLocationSnackbar(val location: String) : MainEvent
    data class SaveLocation(val location: String): MainEvent
    object ShowEmptyMessage : MainEvent
    object RequestLocationPermission : MainEvent
    data class LoadForecastWithLocation(val location: String): MainEvent
}
