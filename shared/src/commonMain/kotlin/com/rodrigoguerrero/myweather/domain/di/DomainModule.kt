package com.rodrigoguerrero.myweather.domain.di

import com.rodrigoguerrero.myweather.domain.interactors.weather.CurrentWeatherInteractor
import com.rodrigoguerrero.myweather.domain.interactors.weather.CurrentWeatherInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.search.DeleteFavoriteLocationInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.DeleteFavoriteLocationInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.weather.ForecastInteractor
import com.rodrigoguerrero.myweather.domain.interactors.weather.ForecastInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationByNameInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationByNameInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationsInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.RetrieveFavoriteLocationsInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.search.SaveFavoriteLocationInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.SaveFavoriteLocationInteractorImpl
import com.rodrigoguerrero.myweather.domain.interactors.search.SearchInteractor
import com.rodrigoguerrero.myweather.domain.interactors.search.SearchInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    factory<CurrentWeatherInteractor> { CurrentWeatherInteractorImpl(repository = get()) }
    factory<ForecastInteractor> { ForecastInteractorImpl(repository = get()) }
    factory<SearchInteractor> { SearchInteractorImpl(repository = get()) }
    factory<SaveFavoriteLocationInteractor> { SaveFavoriteLocationInteractorImpl(dataSource = get()) }
    factory<RetrieveFavoriteLocationsInteractor> {
        RetrieveFavoriteLocationsInteractorImpl(dataSource = get())
    }
    factory<RetrieveFavoriteLocationByNameInteractor> {
        RetrieveFavoriteLocationByNameInteractorImpl(dataSource = get())
    }
    factory<DeleteFavoriteLocationInteractor> { DeleteFavoriteLocationInteractorImpl(dataSource = get()) }
}
