package com.rodrigoguerrero.myweather

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.rodrigoguerrero.myweather.ui.navigation.NavMainScreen
import com.rodrigoguerrero.myweather.ui.theme.AppTheme
import com.rodrigoguerrero.myweather.ui.viewmodels.AppViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    val viewModel = getViewModel(
        key = "app-viewmodel",
        factory = viewModelFactory { AppViewModel() }
    )
    viewModel.BindPermissionController()

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
