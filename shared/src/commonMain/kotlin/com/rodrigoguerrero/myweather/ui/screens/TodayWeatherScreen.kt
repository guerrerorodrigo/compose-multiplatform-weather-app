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
import com.rodrigoguerrero.myweather.ui.components.CurrentDetailsItem
import com.rodrigoguerrero.myweather.ui.components.CurrentWeatherItem
import com.rodrigoguerrero.myweather.ui.components.DividerItem
import com.rodrigoguerrero.myweather.ui.components.ErrorMessage
import com.rodrigoguerrero.myweather.ui.components.HourlyRainItem
import com.rodrigoguerrero.myweather.ui.components.HourlyWeatherItem
import com.rodrigoguerrero.myweather.ui.components.Loader
import com.rodrigoguerrero.myweather.ui.components.PrecipitationChanceItem
import com.rodrigoguerrero.myweather.ui.components.RiseAndSetItem
import com.rodrigoguerrero.myweather.ui.components.SectionTitleItem
import com.rodrigoguerrero.myweather.ui.components.TotalDailyRainVolume
import com.rodrigoguerrero.myweather.ui.components.WindItem
import com.rodrigoguerrero.myweather.ui.models.uistate.MainUiState
import com.rodrigoguerrero.myweather.ui.models.events.TodayWeatherEvent
import com.rodrigoguerrero.myweather.ui.models.uistate.TodayWeatherUiState
import com.rodrigoguerrero.myweather.ui.viewmodels.TodayWeatherViewModel
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TodayWeatherScreen(
    mainUiState: MainUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = getViewModel(
        key = "today-weather-screen",
        factory = viewModelFactory { TodayWeatherViewModel() }
    )

    val state by viewModel.state.collectAsState()

    LaunchedEffect(mainUiState.query) {
        val event = if (mainUiState.query.isNotEmpty()) {
            TodayWeatherEvent.UpdateQuery(query = mainUiState.query)
        } else {
            null
        }
        event?.let { viewModel.onEvent(event) }
    }

    LaunchedEffect(mainUiState.forecast) {
        if (mainUiState.forecast != null) {
            viewModel.onEvent(TodayWeatherEvent.UpdateForecast(mainUiState.forecast))
        }
    }

    LaunchedEffect(mainUiState.isError) {
        if (mainUiState.isError) {
            viewModel.onEvent(TodayWeatherEvent.ShowError)
        }
    }

    LaunchedEffect(mainUiState.isLoading) {
        if (mainUiState.isLoading) {
            viewModel.onEvent(TodayWeatherEvent.ShowLoading)
        }
    }

    when {
        state.isLoading -> Loader(modifier)
        state.isError -> ErrorMessage(modifier, onRetry)
        else -> CurrentWeatherScreenContent(
            todayWeatherUiState = state,
            modifier = modifier,
        )
    }
}

@Composable
private fun CurrentWeatherScreenContent(
    todayWeatherUiState: TodayWeatherUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            CurrentDateItem(currentTime = todayWeatherUiState.currentTime)
        }
        item {
            CurrentWeatherItem(todayWeatherUiState)
        }
        item {
            HourlyWeatherItem(hourlyForecasts = todayWeatherUiState.hourlyForecasts)
        }
        item {
            PrecipitationChanceItem(todayWeatherUiState)
        }
        item {
            DividerItem()
        }
        item {
            CurrentDetailsItem(todayWeatherUiState)
        }
        item {
            DividerItem()
        }
        item {
            SectionTitleItem(
                title = stringResource(MR.strings.precipitation),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        item {
            HourlyRainItem(
                hourlyForecasts = todayWeatherUiState.hourlyForecasts,
            )
        }
        item {
            TotalDailyRainVolume(
                totalPrecipitation = stringResource(
                    MR.strings.total_precipitation_mm,
                    todayWeatherUiState.totalPrecipitation,
                ),
            )
        }
        item {
            DividerItem()
        }
        item {
            WindItem(modifier, todayWeatherUiState)
        }
        item {
            DividerItem()
        }
        item {
            RiseAndSetItem(
                setTime = todayWeatherUiState.sunset,
                riseTime = todayWeatherUiState.sunrise,
                riseTitle = stringResource(MR.strings.sunrise),
                setTitle = stringResource(MR.strings.sunset),
                sectionTitle = stringResource(MR.strings.sunrise_sunset),
            )
        }
        item {
            DividerItem()
        }
        item {
            RiseAndSetItem(
                setTime = todayWeatherUiState.moonset,
                riseTime = todayWeatherUiState.moonrise,
                riseTitle = stringResource(MR.strings.moonrise),
                setTitle = stringResource(MR.strings.moonset),
                sectionTitle = stringResource(MR.strings.moonrise_moonset),
            )
        }
        item {
            DividerItem()
        }
    }
}
