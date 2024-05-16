package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.temperature_degree_symbol
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HourlyForecast(
    hourlyForecast: HourUi,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(Res.string.temperature_degree_symbol, hourlyForecast.temperature),
            style = MaterialTheme.typography.labelMedium,
        )
        AsyncImage(
            model = hourlyForecast.iconUrl,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
