package com.rodrigoguerrero.myweather.ui.di

import com.rodrigoguerrero.myweather.ui.viewmodels.FutureDaysWeatherViewModel
import com.rodrigoguerrero.myweather.ui.viewmodels.MainViewModel
import com.rodrigoguerrero.myweather.ui.viewmodels.SearchViewModel
import com.rodrigoguerrero.myweather.ui.viewmodels.TodayWeatherViewModel
import com.rodrigoguerrero.myweather.ui.viewmodels.TomorrowWeatherViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { MainViewModel() }
    factory { FutureDaysWeatherViewModel() }
    factory { SearchViewModel() }
    factory { TodayWeatherViewModel() }
    factory { TomorrowWeatherViewModel() }
}
