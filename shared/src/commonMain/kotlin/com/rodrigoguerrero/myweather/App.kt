package com.rodrigoguerrero.myweather

import androidx.compose.runtime.Composable
import com.rodrigoguerrero.myweather.ui.navigation.RootNavGraph
import com.rodrigoguerrero.myweather.ui.theme.AppTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        RootNavGraph()
    }
}
