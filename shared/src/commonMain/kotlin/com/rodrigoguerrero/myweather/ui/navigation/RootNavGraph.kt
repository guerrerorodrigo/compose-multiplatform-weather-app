package com.rodrigoguerrero.myweather.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.myweather.ui.screens.MainScreen
import com.rodrigoguerrero.myweather.ui.screens.SearchScreen

@Composable
fun RootNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "/home",
    ) {
        composable("/home") {
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                onNavigateToSearch = { navController.navigate("/search") },
            )
        }
        composable("/search") {
            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                onClose = { navController.popBackStack() }
            )
        }
    }
}
