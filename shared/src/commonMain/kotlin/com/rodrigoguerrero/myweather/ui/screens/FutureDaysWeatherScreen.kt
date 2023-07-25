package com.rodrigoguerrero.myweather.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rodrigoguerrero.myweather.ui.components.DayItem
import com.rodrigoguerrero.myweather.ui.components.DividerItem
import com.rodrigoguerrero.myweather.ui.components.ErrorMessage
import com.rodrigoguerrero.myweather.ui.components.Loader
import com.rodrigoguerrero.myweather.ui.models.events.FutureWeatherEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.MainUiState
import com.rodrigoguerrero.myweather.ui.viewmodels.FutureDaysWeatherViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun FutureDaysWeatherScreen(
    mainUiState: MainUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel(
        key = "future-weather-screen",
        factory = viewModelFactory { FutureDaysWeatherViewModel() }
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(mainUiState.query, mainUiState.forecast) {
        viewModel.onEvent(
            FutureWeatherEvent.UpdateDays(
                days = mainUiState.forecast?.forecastDays.orEmpty(),
                timeZoneId = mainUiState.forecast?.location?.timeZoneId.orEmpty(),
                currentTime = mainUiState.forecast?.location?.localTimeEpoch ?: 0,
            )
        )
    }

    LaunchedEffect(mainUiState.isError) {
        if (mainUiState.isError) {
            viewModel.onEvent(FutureWeatherEvent.ShowError)
        }
    }

    LaunchedEffect(mainUiState.isLoading) {
        if (mainUiState.isLoading) {
            viewModel.onEvent(FutureWeatherEvent.ShowLoading)
        }
    }

    when {
        state.isLoading -> Loader(modifier)
        state.isError -> ErrorMessage(modifier, onRetry)
        else -> LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            items(state.days) { day ->
                DayItem(day = day)
                DividerItem()
            }
        }
    }
}
