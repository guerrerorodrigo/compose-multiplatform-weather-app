package com.rodrigoguerrero.myweather.ui.models.events

sealed interface AppEvent {
    data class SaveLocation(val location: String) : AppEvent
    object RequestLocationPermission : AppEvent
    object OnPermissionGranted : AppEvent
    object OnPermissionDenied : AppEvent
    object OnPermissionPermanentlyDenied : AppEvent
}
