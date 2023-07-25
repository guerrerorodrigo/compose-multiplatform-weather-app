package com.rodrigoguerrero.myweather

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.rodrigoguerrero.myweather.ui.models.events.AppEvent
import com.rodrigoguerrero.myweather.ui.navigation.NavMainScreen
import com.rodrigoguerrero.myweather.ui.theme.AppTheme
import com.rodrigoguerrero.myweather.ui.viewmodels.AppViewModel
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    val viewModel = getViewModel(
        key = "app-viewmodel",
        factory = viewModelFactory { AppViewModel() }
    )
    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(MR.strings.add_location_to_list)
    val snackbarAction = stringResource(MR.strings.add)

    viewModel.BindPermissionController()

    LaunchedEffect(query, state.isLocationSaved) {
        if (query.isNotEmpty() && !state.isLocationSaved) {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = snackbarMessage,
                actionLabel = snackbarAction,
                duration = SnackbarDuration.Long,
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.onEvent(AppEvent.SaveLocation(query))
                }
            }
        }
    }

    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) {
            if (state.isLocationPermissionDenied != null) {
                Navigator(
                    screen = NavMainScreen(
                        onQueryUpdated = {
                            query = it
                            viewModel.isLocationSaved(query)
                        },
                        isLocationPermissionDenied = state.isLocationPermissionDenied,
                    ),
                ) { navigator ->
                    FadeTransition(navigator)
                }
            }
        }
    }
}
