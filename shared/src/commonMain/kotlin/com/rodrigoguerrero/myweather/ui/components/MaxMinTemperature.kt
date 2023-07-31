package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MaxMinTemperature(
    maxTemp: String,
    minTemp: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = stringResource(MR.strings.min_max_temperatures, maxTemp, minTemp),
            modifier = Modifier.fillMaxWidth(),
            style = style,
        )
    }
}
