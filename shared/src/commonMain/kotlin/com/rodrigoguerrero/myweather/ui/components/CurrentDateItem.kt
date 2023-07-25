package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrentDateItem(
    currentTime: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = currentTime,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}
