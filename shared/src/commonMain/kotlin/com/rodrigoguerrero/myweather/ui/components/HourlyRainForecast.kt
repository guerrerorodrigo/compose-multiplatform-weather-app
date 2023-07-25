package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HourlyRainForecast(
    hourlyForecast: HourUi,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(MR.strings.chance_rain_percentage, hourlyForecast.chanceOfRain),
            style = MaterialTheme.typography.labelMedium,
        )
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(16.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .height((32 * hourlyForecast.precipitationMmPercentage).dp)
                    .width(16.dp)
                    .background(color = Color.Cyan)
            )
        }
        Text(
            text = hourlyForecast.precipitationMm,
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
