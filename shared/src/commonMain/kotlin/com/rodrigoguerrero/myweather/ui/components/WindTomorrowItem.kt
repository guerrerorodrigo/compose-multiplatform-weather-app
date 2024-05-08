package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.max_wind_speed
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WindTomorrowItem(
    windForce: StringResource?,
    maxWindSpeed: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        windForce?.let { forceRes ->
            Text(
                text = stringResource(forceRes),
            )
        }
        Text(
            text = stringResource(Res.string.max_wind_speed, maxWindSpeed),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
