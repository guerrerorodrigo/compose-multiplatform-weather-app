package com.rodrigoguerrero.myweather.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rodrigoguerrero.myweather.ui.theme.DarkColorScheme
import com.rodrigoguerrero.myweather.ui.theme.LightColorScheme
import com.rodrigoguerrero.myweather.ui.theme.Typography

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
