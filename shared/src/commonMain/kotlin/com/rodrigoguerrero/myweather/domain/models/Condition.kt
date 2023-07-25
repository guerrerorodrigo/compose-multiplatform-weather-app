package com.rodrigoguerrero.myweather.domain.models

import com.rodrigoguerrero.myweather.data.remote.models.dtos.ConditionDto

data class Condition(
    val condition: String,
    val iconUrl: String,
    val code: Int,
)

internal fun ConditionDto.toDomain() = Condition(
    condition = condition,
    iconUrl = iconUrl,
    code = code,
)
