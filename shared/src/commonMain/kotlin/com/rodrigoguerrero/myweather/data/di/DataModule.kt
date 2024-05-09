package com.rodrigoguerrero.myweather.data.di

import com.rodrigoguerrero.myweather.data.local.datastore.DataStorePreferencesRepository
import com.rodrigoguerrero.myweather.data.local.datastore.PreferencesRepository
import com.rodrigoguerrero.myweather.data.local.db.LocationDataSource
import com.rodrigoguerrero.myweather.data.local.db.RoomLocationDataSource
import com.rodrigoguerrero.myweather.data.local.db.room.AppDatabase
import com.rodrigoguerrero.myweather.data.local.db.room.DatabaseBuilderProvider
import com.rodrigoguerrero.myweather.data.remote.config.createHttpClient
import com.rodrigoguerrero.myweather.data.remote.repositories.KtorWeatherRepositoryImpl
import com.rodrigoguerrero.myweather.data.remote.repositories.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<PreferencesRepository> { DataStorePreferencesRepository(dataStoreProvider = get()) }
    single<WeatherRepository> { KtorWeatherRepositoryImpl(httpClient = get()) }
    single { createHttpClient(httpClientEngine = get()) }
    single<LocationDataSource> { RoomLocationDataSource(db = get()) }
    single<AppDatabase> { get<DatabaseBuilderProvider>().getDatabaseBuilder().build() }
}
