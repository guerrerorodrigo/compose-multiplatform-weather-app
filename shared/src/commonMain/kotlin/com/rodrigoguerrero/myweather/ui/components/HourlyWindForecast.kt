package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi

@Composable
fun HourlyWindForecast(
    hourlyForecast: HourUi,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .rotate(-90f + hourlyForecast.windDirectionDegrees)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = hourlyForecast.windSpeed.toString(),
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height((hourlyForecast.windSpeed.coerceAtMost(32)).dp)
                .width(16.dp)
                .background(color = color)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = hourlyForecast.hour,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
