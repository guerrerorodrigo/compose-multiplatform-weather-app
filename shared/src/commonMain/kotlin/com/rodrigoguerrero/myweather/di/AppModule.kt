package com.rodrigoguerrero.myweather.di

import com.rodrigoguerrero.myweather.data.di.dataModule
import com.rodrigoguerrero.myweather.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dataModule,
        domainModule,
    )
}
