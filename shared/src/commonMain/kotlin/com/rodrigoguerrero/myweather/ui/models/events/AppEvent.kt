package com.rodrigoguerrero.myweather.ui.models.events

sealed interface AppEvent {
    object RequestLocationPermission : AppEvent
    object OnPermissionGranted : AppEvent
    object OnPermissionDenied : AppEvent
    object OnPermissionPermanentlyDenied : AppEvent
}
