package com.rodrigoguerrero.myweather.ui.models

data class PermissionsState(
    val isGranted: Boolean? = null,
    val isPermanentlyDenied: Boolean? = null,
    val isDenied: Boolean? = null,
)
