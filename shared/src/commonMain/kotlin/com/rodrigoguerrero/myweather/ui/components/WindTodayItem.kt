package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rodrigoguerrero.myweather.ui.models.uistate.TodayWeatherUiState
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WindTodayItem(
    todayWeatherUiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = todayWeatherUiState.windSpeed.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = todayWeatherUiState.windSpeedColor,
            fontSize = 56.sp,
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(-90f + todayWeatherUiState.windDirectionDegrees)
            )
            Text(
                text = stringResource(MR.strings.km_h),
                style = MaterialTheme.typography.labelMedium,
            )
        }

        Column(
            modifier = Modifier.padding(start = 16.dp),
        ) {
            Text(
                text = stringResource(todayWeatherUiState.windSpeedDescription),
            )
            todayWeatherUiState.windDirection?.let {
                Text(
                    text = stringResource(todayWeatherUiState.windDirection),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}
