package com.rodrigoguerrero.myweather.common

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun formatTime(currentTime: LocalDateTime): String {
    val time = currentTime.time.toString()
    val formattedTime = time.substring(startIndex = 0, endIndex = time.lastIndexOf(":"))
    return "${currentTime.dayOfMonth} ${
        currentTime.month.name.lowercase().capitalize(Locale.current)
    }, $formattedTime"
}

fun formatTimeWithDayName(currentTime: LocalDateTime): String {
    return "${currentTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }}, " +
            "${currentTime.dayOfMonth} " + currentTime.month.name.lowercase().capitalize(Locale.current)
}

fun parseTime(localTimeEpoch: Long, timeZoneId: String) =
    LocalDateTime.parse(
        Instant.fromEpochSeconds(localTimeEpoch)
            .toLocalDateTime(TimeZone.of(timeZoneId)).toString()
    )

fun isToday(
    localTimeEpoch: Long,
    currentTimeEpoch: Long,
    localTimeEpochTimeZoneId: String,
    currentTimeEpochTimeZoneId: String,
): Boolean {
    val time = parseTime(localTimeEpoch, localTimeEpochTimeZoneId)
    val now = parseTime(currentTimeEpoch, currentTimeEpochTimeZoneId)
    return time.year == now.year && time.month == now.month && time.dayOfMonth == now.dayOfMonth
}

fun getHour(localTimeEpoch: Long, timeZoneId: String): Int {
    return parseTime(localTimeEpoch, timeZoneId).hour
}

fun isSameDay(currentTimeEpoch: Long, localTimeEpoch: Long, timeZoneId: String): Boolean {
    val currentDateTime = parseTime(currentTimeEpoch, timeZoneId)
    val localTime = parseTime(localTimeEpoch, timeZoneId)

    return currentDateTime.dayOfMonth == localTime.dayOfMonth &&
            currentDateTime.monthNumber == localTime.monthNumber &&
            currentDateTime.year == localTime.year
}
