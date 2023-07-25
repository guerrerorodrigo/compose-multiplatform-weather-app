package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uistate.TodayWeatherUiState

@Composable
fun HourlyWindForecastItem(
    modifier: Modifier,
    todayWeatherUiState: TodayWeatherUiState
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(todayWeatherUiState.hourlyForecasts) { hour ->
            HourlyWindForecast(
                hourlyForecast = hour,
                color = todayWeatherUiState.getColor(hour.windSpeed),
            )
        }
    }
}
