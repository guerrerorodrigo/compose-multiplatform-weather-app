package com.rodrigoguerrero.myweather.domain.interactors.weather

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.domain.models.CurrentWeather
import com.rodrigoguerrero.myweather.domain.models.Forecast
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import kotlinx.coroutines.flow.Flow

interface ForecastInteractor {

    suspend operator fun invoke(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int,
    ): Flow<ResourceResult<Forecast>>
}
