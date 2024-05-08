package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uistate.TomorrowWeatherUiState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.chance_rain_percentage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TomorrowWeatherItem(
    state: TomorrowWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f),
        ) {
            MaxMinTemperature(
                maxTemp = state.maxTemperature,
                minTemp = state.minTemperature,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = state.condition,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.rainChance.toInt() > 0) {
                Text(
                    text = stringResource(Res.string.chance_rain_percentage, state.rainChance),
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Cyan),
                )
            }
            KamelImage(
                resource = asyncPainterResource(data = state.conditionIconUrl),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
            )
        }
    }
}
