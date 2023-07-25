package com.rodrigoguerrero.myweather.ui.models.uistate

import com.rodrigoguerrero.myweather.domain.models.Forecast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val query: String = "",
    val forecast: Forecast? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isDefaultQuery: Boolean = true,
)

internal fun MutableStateFlow<MainUiState>.updateQuery(query: String) {
    update { it.copy(query = query) }
}

internal fun MutableStateFlow<MainUiState>.isLoading() {
    update { it.copy(isLoading = true) }
}

internal fun MutableStateFlow<MainUiState>.isError() {
    update { it.copy(isError = true, isLoading = false) }
}

internal fun MutableStateFlow<MainUiState>.updateForecast(forecast: Forecast) {
    update { it.copy(isLoading = false, isError = false, forecast = forecast) }
}
