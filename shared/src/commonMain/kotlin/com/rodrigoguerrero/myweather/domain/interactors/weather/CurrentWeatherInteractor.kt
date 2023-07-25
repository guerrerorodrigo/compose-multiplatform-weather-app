package com.rodrigoguerrero.myweather.domain.interactors.weather

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.domain.models.CurrentWeather
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherInteractor {

    suspend operator fun invoke(
        query: String,
        airQuality: AirQuality
    ): Flow<ResourceResult<CurrentWeather>>
}
