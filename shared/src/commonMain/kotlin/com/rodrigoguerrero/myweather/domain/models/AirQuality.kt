package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.AirQualityDto

data class AirQuality(
    val carbonMonoxide: Double,
    val nitrogenDioxide: Double,
    val ozone: Double,
    val sulphurDioxide: Double,
    val pm2_5: Double,
    val pm10: Double,
    val usEpaIndex: Int,
    val gbDefraIndex: Int,
)

internal fun AirQualityDto.toDomain() = AirQuality(
    carbonMonoxide = carbonMonoxide ?: 0.0,
    nitrogenDioxide = nitrogenDioxide ?: 0.0,
    ozone = ozone ?: 0.0,
    sulphurDioxide = sulphurDioxide ?: 0.0,
    pm2_5 = pm2_5 ?: 0.0,
    pm10 = pm10 ?: 0.0,
    usEpaIndex = usEpaIndex ?: 0,
    gbDefraIndex = gbDefraIndex ?: 0,
)
