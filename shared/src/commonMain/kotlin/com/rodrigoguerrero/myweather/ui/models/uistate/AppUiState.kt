package com.rodrigoguerrero.myweather.ui.models.uistate

data class AppUiState(
    val isLocationSaved: Boolean = false,
    val isLocationPermissionDenied: Boolean? = null,
)
