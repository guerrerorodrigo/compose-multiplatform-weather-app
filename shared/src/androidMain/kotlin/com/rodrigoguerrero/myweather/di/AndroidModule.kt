package com.rodrigoguerrero.myweather.di

import com.rodrigoguerrero.myweather.data.local.db.AndroidDatabaseBuilderProvider
import com.rodrigoguerrero.myweather.data.local.DataStoreProviderImpl
import com.rodrigoguerrero.myweather.data.local.datastore.DataStoreProvider
import com.rodrigoguerrero.myweather.data.local.db.room.DatabaseBuilderProvider
import com.rodrigoguerrero.myweather.domain.location.AndroidLocationService
import com.rodrigoguerrero.myweather.domain.location.LocationService
import dev.icerock.moko.permissions.PermissionsController
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<DataStoreProvider> { DataStoreProviderImpl(androidContext()) }
    single { Android.create() }
    single<DatabaseBuilderProvider> { AndroidDatabaseBuilderProvider(context = androidContext()) }
    single { PermissionsController(applicationContext = androidContext())}
    factory <LocationService> { AndroidLocationService(context = androidContext()) }
}
