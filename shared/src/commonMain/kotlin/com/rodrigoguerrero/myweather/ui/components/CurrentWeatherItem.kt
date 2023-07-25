package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uistate.TodayWeatherUiState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CurrentWeatherItem(
    todayWeatherUiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Temperature(
            currentTemperature = todayWeatherUiState.currentTemperature,
            feelsLike = todayWeatherUiState.feelsLikeTemperature,
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KamelImage(
                resource = asyncPainterResource(data = todayWeatherUiState.conditionIconUrl),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
            )
            Text(text = todayWeatherUiState.condition)
        }
    }
}
