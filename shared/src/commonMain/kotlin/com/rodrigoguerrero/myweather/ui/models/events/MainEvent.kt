package com.rodrigoguerrero.myweather.ui.models.events

sealed interface MainEvent {
    data class UpdateQuery(val query: String) : MainEvent
    object LoadForecast : MainEvent
    object Loading : MainEvent
    object Error : MainEvent
}
