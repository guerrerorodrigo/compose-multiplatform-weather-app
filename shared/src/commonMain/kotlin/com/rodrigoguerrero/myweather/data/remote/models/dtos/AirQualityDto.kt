package com.rodrigoguerrero.myweather.data.remote.models.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityDto(
    @SerialName("co") val carbonMonoxide: Double? = null,
    @SerialName("no2") val nitrogenDioxide: Double? = null,
    @SerialName("o3") val ozone: Double? = null,
    @SerialName("so2") val sulphurDioxide: Double? = null,
    @SerialName("pm2_5") val pm2_5: Double? = null,
    @SerialName("pm10") val pm10: Double? = null,
    @SerialName("us-epa-index") val usEpaIndex: Int? = null,
    @SerialName("gb-defra-index") val gbDefraIndex: Int? = null,
)
