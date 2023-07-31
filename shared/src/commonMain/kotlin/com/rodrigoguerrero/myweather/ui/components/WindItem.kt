package com.rodrigoguerrero.myweather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.myweather.ui.models.uimodels.HourUi
import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.compose.stringResource

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
        SectionTitleItem(title = stringResource(MR.strings.wind))
        headerSection()
        HourlyWindForecastItem(hourlyForecasts = hourlyForecasts)
    }
}
