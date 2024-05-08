package com.rodrigoguerrero.myweather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.components.CurrentDateItem
import com.rodrigoguerrero.myweather.ui.components.DividerItem
import com.rodrigoguerrero.myweather.ui.components.ErrorMessage
import com.rodrigoguerrero.myweather.ui.components.HourlyRainItem
import com.rodrigoguerrero.myweather.ui.components.HourlyWeatherItem
import com.rodrigoguerrero.myweather.ui.components.Loader
import com.rodrigoguerrero.myweather.ui.components.PrecipitationChanceItem
import com.rodrigoguerrero.myweather.ui.components.SectionTitleItem
import com.rodrigoguerrero.myweather.ui.components.TomorrowDetailsItem
import com.rodrigoguerrero.myweather.ui.components.TomorrowWeatherItem
import com.rodrigoguerrero.myweather.ui.components.TotalDailyRainVolume
import com.rodrigoguerrero.myweather.ui.components.WindItem
import com.rodrigoguerrero.myweather.ui.components.WindTomorrowItem
import com.rodrigoguerrero.myweather.ui.models.events.TomorrowWeatherEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.MainUiState
import com.rodrigoguerrero.myweather.ui.models.uistate.TomorrowWeatherUiState
import com.rodrigoguerrero.myweather.ui.viewmodels.TomorrowWeatherViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.precipitation
import myweather.shared.generated.resources.tomorrow_rain_chance
import myweather.shared.generated.resources.total_precipitation_mm
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun TomorrowWeatherScreen(
    mainUiState: MainUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel(
        key = "tomorrow-weather-screen",
        factory = viewModelFactory { TomorrowWeatherViewModel() }
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(mainUiState.forecast) {
        if (mainUiState.forecast != null) {
            viewModel.onEvent(TomorrowWeatherEvent.UpdateForecast(mainUiState.forecast))
        }
    }

    LaunchedEffect(mainUiState.isError) {
        if (mainUiState.isError) {
            viewModel.onEvent(TomorrowWeatherEvent.ShowError)
        }
    }

    LaunchedEffect(mainUiState.isLoading) {
        if (mainUiState.isLoading) {
            viewModel.onEvent(TomorrowWeatherEvent.ShowLoading)
        }
    }

    when {
        state.isLoading -> Loader(modifier)
        state.isError -> ErrorMessage(modifier, onRetry)
        else -> TomorrowWeatherScreenContent(
            tomorrowWeatherUiState = state,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TomorrowWeatherScreenContent(
    tomorrowWeatherUiState: TomorrowWeatherUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            CurrentDateItem(currentTime = tomorrowWeatherUiState.date)
        }
        item {
            TomorrowWeatherItem(
                state = tomorrowWeatherUiState,
            )
        }
        item {
            HourlyWeatherItem(hourlyForecasts = tomorrowWeatherUiState.hourlyForecasts)
        }
        item {
            PrecipitationChanceItem(
                rainChance = stringResource(
                    Res.string.tomorrow_rain_chance,
                    tomorrowWeatherUiState.rainChance,
                )
            )
        }
        item {
            DividerItem()
        }
        item {
            TomorrowDetailsItem(state = tomorrowWeatherUiState)
        }
        item {
            DividerItem()
        }
        item {
            SectionTitleItem(
                title = stringResource(Res.string.precipitation),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        item {
            HourlyRainItem(
                hourlyForecasts = tomorrowWeatherUiState.hourlyForecasts,
            )
        }
        item {
            TotalDailyRainVolume(
                totalPrecipitation = stringResource(
                    Res.string.total_precipitation_mm,
                    tomorrowWeatherUiState.totalDailyVolumeRain,
                ),
            )
        }
        item {
            DividerItem()
        }
        item {
            WindItem(
                hourlyForecasts = tomorrowWeatherUiState.hourlyForecasts,
                headerSection = {
                    WindTomorrowItem(
                        maxWindSpeed = tomorrowWeatherUiState.maxWindSpeed,
                        windForce = tomorrowWeatherUiState.windForce,
                    )
                }
            )
        }
        item {
            DividerItem()
        }
    }
}
