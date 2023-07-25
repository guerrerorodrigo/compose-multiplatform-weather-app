package com.rodrigoguerrero.myweather.data.di

import com.rodrigoguerrero.myweather.data.local.datastore.DataStorePreferencesRepository
import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource
import com.rodrigoguerrero.myweather.data.local.datastore.PreferencesRepository
import com.rodrigoguerrero.myweather.data.local.db.SqDelightLocationDataSource
import com.rodrigoguerrero.myweather.data.remote.config.createHttpClient
import com.rodrigoguerrero.myweather.data.remote.repositories.KtorWeatherRepositoryImpl
import com.rodrigoguerrero.myweather.data.remote.repositories.WeatherRepository
import com.rodrigoguerrero.mywheather.database.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single<PreferencesRepository> { DataStorePreferencesRepository(dataStoreProvider = get()) }
    single<WeatherRepository> { KtorWeatherRepositoryImpl(httpClient = get()) }
    single { createHttpClient(httpClientEngine = get()) }
    single<LocationDataSource> {
        SqDelightLocationDataSource(
            database = AppDatabase(driver = get())
        )
    }
}
