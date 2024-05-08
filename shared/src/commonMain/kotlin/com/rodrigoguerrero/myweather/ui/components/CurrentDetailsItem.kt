package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uistate.TodayWeatherUiState
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.current_details
import myweather.shared.generated.resources.dew_point
import myweather.shared.generated.resources.humidity
import myweather.shared.generated.resources.humidity_percentage
import myweather.shared.generated.resources.pressure
import myweather.shared.generated.resources.pressure_mbar
import myweather.shared.generated.resources.temperature_celsius
import myweather.shared.generated.resources.uv_index
import myweather.shared.generated.resources.visibility
import myweather.shared.generated.resources.visibility_km
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurrentDetailsItem(
    todayWeatherUiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitleItem(
            title = stringResource(Res.string.current_details),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            DetailsHeaders()
            DetailsValues(todayWeatherUiState)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.DetailsValues(todayWeatherUiState: TodayWeatherUiState) {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(Res.string.humidity_percentage, todayWeatherUiState.humidity),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.temperature_celsius, todayWeatherUiState.dewPoint),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.pressure_mbar, todayWeatherUiState.pressure),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = todayWeatherUiState.uvIndex,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(Res.string.visibility_km, todayWeatherUiState.visibility),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.DetailsHeaders() {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(Res.string.humidity),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(Res.string.dew_point),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(Res.string.pressure),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(Res.string.uv_index),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(Res.string.visibility),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
    }
}
