package com.rodrigoguerrero.myweather.di

import com.rodrigoguerrero.myweather.data.local.datastore.DataStoreProvider
import com.rodrigoguerrero.myweather.data.local.DataStoreProviderImpl
import com.rodrigoguerrero.myweather.domain.location.AndroidLocationService
import com.rodrigoguerrero.myweather.domain.location.LocationService
import com.rodrigoguerrero.mywheather.database.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.icerock.moko.permissions.PermissionsController
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<DataStoreProvider> { DataStoreProviderImpl(androidContext()) }
    single { Android.create() }
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = androidContext(),
            name = "location.db",
        )
    }
    single { PermissionsController(applicationContext = androidContext())}
    single<LocationService> { AndroidLocationService(context = androidContext()) }
}
