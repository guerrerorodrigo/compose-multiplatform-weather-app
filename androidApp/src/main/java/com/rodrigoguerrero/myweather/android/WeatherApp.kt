package com.rodrigoguerrero.myweather.android

import android.app.Application
import com.rodrigoguerrero.myweather.di.appModule
import com.rodrigoguerrero.myweather.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(appModule)
        }
    }
}
