package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi
import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.wind
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WindItem(
    modifier: Modifier = Modifier,
    headerSection: @Composable () -> Unit,
    hourlyForecasts: List<HourUi>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SectionTitleItem(title = stringResource(Res.string.wind))
        headerSection()
        HourlyWindForecastItem(hourlyForecasts = hourlyForecasts)
    }
}
