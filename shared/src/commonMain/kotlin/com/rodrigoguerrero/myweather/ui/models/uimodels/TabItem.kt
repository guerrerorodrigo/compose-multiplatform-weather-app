@file:OptIn(ExperimentalResourceApi::class, ExperimentalResourceApi::class)

package com.rodrigoguerrero.myweather.ui.models.uimodels

import myweather.shared.generated.resources.Res
import myweather.shared.generated.resources.tab_name_three_days
import myweather.shared.generated.resources.tab_name_today
import myweather.shared.generated.resources.tab_name_tomorrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
data class TabItem(
    val title: StringResource,
)

val tabs = listOf(
    TabItem(
        title = Res.string.tab_name_today,
    ),
    TabItem(
        title = Res.string.tab_name_tomorrow,
    ),
    TabItem(
        title = Res.string.tab_name_three_days,
    ),
)
