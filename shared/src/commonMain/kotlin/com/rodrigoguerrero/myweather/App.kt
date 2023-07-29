package com.rodrigoguerrero.myweather

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.rodrigoguerrero.myweather.ui.navigation.NavMainScreen
import com.rodrigoguerrero.myweather.ui.theme.AppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        Navigator(
            screen = NavMainScreen,
        ) { navigator ->
            FadeTransition(navigator)
        }
    }
}
