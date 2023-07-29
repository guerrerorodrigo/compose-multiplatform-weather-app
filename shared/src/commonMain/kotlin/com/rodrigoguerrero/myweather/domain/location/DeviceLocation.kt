package com.rodrigoguerrero.myweather.domain.location

data class DeviceLocation(
    val latitude: Double,
    val longitude: Double,
) {
    override fun toString(): String {
        return "$latitude,$longitude"
    }
}
