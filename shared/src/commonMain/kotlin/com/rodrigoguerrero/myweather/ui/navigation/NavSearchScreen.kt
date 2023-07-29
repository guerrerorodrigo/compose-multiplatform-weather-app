package com.rodrigoguerrero.myweather.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.rodrigoguerrero.myweather.ui.screens.SearchScreen

object NavSearchScreen: Screen {

    @Composable
    override fun Content() {
        SearchScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
