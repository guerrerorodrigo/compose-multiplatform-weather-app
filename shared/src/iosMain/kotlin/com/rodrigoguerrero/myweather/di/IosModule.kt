package com.rodrigoguerrero.myweather.di

import com.rodrigoguerrero.myweather.data.local.DataStoreProviderImpl
import com.rodrigoguerrero.myweather.data.local.datastore.DataStoreProvider
import com.rodrigoguerrero.mywheather.database.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.icerock.moko.permissions.ios.PermissionsController
import dev.icerock.moko.permissions.ios.PermissionsControllerProtocol
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

val appModule = module {
    single<DataStoreProvider> { DataStoreProviderImpl() }
    single { Darwin.create() }
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "location.db")
    }
    single<PermissionsControllerProtocol> { PermissionsController() }
}
