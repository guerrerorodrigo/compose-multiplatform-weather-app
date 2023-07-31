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
import com.rodrigoguerrero.myweather.ui.models.uistate.TomorrowWeatherUiState
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TomorrowDetailsItem(
    state: TomorrowWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitleItem(
            title = stringResource(MR.strings.current_details),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            DetailsHeaders()
            DetailsValues(state)
        }
    }
}

@Composable
private fun RowScope.DetailsValues(state: TomorrowWeatherUiState) {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(MR.strings.humidity_percentage, state.humidity),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = state.uvIndex,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(MR.strings.chance_rain_percentage, state.rainChance),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = state.sunsetSunrise,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun RowScope.DetailsHeaders() {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(MR.strings.humidity),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.uv_index),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.chance_of_rain),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        Text(
            text = stringResource(MR.strings.sunrise_sunset_tomorrow_details),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
    }
}
