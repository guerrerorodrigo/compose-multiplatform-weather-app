package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.ForecastDayDto

data class ForecastDay(
    val date: String,
    val dateEpoch: Long,
    val day: Day,
    val astronomy: Astronomy,
    val hours: List<Hour>,
)

internal fun ForecastDayDto.toDomain() = ForecastDay(
    date = date,
    dateEpoch = dateEpoch,
    day = day.toDomain(),
    astronomy = astronomy.toDomain(),
    hours = hours.map { it.toDomain() },
)
