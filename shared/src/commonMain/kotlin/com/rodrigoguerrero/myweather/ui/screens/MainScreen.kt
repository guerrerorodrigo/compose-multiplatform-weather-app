package com.rodrigoguerrero.myweather.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.myweather.ui.components.EmptyLocationMessage
import com.rodrigoguerrero.myweather.ui.components.LocationTopBar
import com.rodrigoguerrero.myweather.ui.models.events.MainEvent
import com.rodrigoguerrero.myweather.ui.models.uimodels.tabs
import com.rodrigoguerrero.myweather.ui.viewmodels.MainViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.launch
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.add
import myweather.shared.generated.resources.add_location_to_list
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun MainScreen(
    onNavigateToSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(Res.string.add_location_to_list)
    val snackbarAction = stringResource(Res.string.add)
    val pagerState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = getViewModel(
        key = "main-screen",
        factory = viewModelFactory { MainViewModel() }
    )
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.locationToSave) {
        if (state.locationToSave.isNotEmpty()) {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = snackbarMessage,
                actionLabel = snackbarAction,
                duration = SnackbarDuration.Long,
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.onEvent(MainEvent.SaveLocation(state.locationToSave))
                }
            }
        }
    }

    viewModel.BindPermissionController()

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            LocationTopBar(
                query = state.query,
                onNavigateToSearch = onNavigateToSearch,
            )
        },
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column {
                TabRow(
                    selectedTabIndex = pagerState.currentPage
                ) {
                    tabs.forEachIndexed { index, tabItem ->
                        Tab(
                            selected = index == pagerState.currentPage,
                            text = { Text(text = stringResource(tabItem.title)) },
                            onClick = {
                                coroutineScope.launch { pagerState.animateScrollToPage(index) }
                            }
                        )
                    }
                }
                if (state.showEmptyMessage) {
                    EmptyLocationMessage()
                } else {
                    HorizontalPager(state = pagerState) { tabIndex ->
                        when (tabIndex) {
                            0 -> TodayWeatherScreen(
                                mainUiState = state,
                                onRetry = { viewModel.onEvent(MainEvent.LoadForecast) },
                            )

                            1 -> TomorrowWeatherScreen(
                                mainUiState = state,
                                onRetry = { viewModel.onEvent(MainEvent.LoadForecast) },
                            )
                            2 -> FutureDaysWeatherScreen(
                                mainUiState = state,
                                onRetry = { viewModel.onEvent(MainEvent.LoadForecast) },
                            )
                        }
                    }
                }
            }
        }
    }
}
