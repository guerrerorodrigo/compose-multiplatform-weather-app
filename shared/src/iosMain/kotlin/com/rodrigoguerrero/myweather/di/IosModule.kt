package com.rodrigoguerrero.myweather.di

import com.rodrigoguerrero.myweather.data.local.DataStoreProviderImpl
import com.rodrigoguerrero.myweather.data.local.datastore.DataStoreProvider
import com.rodrigoguerrero.myweather.data.local.db.IosDatabaseBuilderProvider
import com.rodrigoguerrero.myweather.data.local.db.room.DatabaseBuilderProvider
import com.rodrigoguerrero.myweather.domain.location.IosLocationService
import com.rodrigoguerrero.myweather.domain.location.LocationService
import dev.icerock.moko.permissions.ios.PermissionsController
import dev.icerock.moko.permissions.ios.PermissionsControllerProtocol
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

val appModule = module {
    single<DataStoreProvider> { DataStoreProviderImpl() }
    single { Darwin.create() }
    single<DatabaseBuilderProvider> { IosDatabaseBuilderProvider() }
    single<PermissionsControllerProtocol> { PermissionsController() }
    single<LocationService> { IosLocationService() }
}
