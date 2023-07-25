package com.rodrigoguerrero.myweather.ui.models.uimodels

import com.rodrigoguerrero.mywheather.MR
import dev.icerock.moko.resources.StringResource

data class TabItem(
    val title: StringResource,
)

val tabs = listOf(
    TabItem(
        title = MR.strings.tab_name_today,
    ),
    TabItem(
        title = MR.strings.tab_name_tomorrow,
    ),
    TabItem(
        title = MR.strings.tab_name_three_days,
    ),
)
