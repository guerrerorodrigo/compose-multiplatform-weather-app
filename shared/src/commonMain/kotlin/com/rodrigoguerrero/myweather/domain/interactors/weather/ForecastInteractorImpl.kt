package com.rodrigoguerrero.myweather.domain.interactors.weather

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.data.remote.repositories.WeatherRepository
import com.rodrigoguerrero.myweather.domain.models.Forecast
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.domain.models.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ForecastInteractorImpl(
    private val repository: WeatherRepository,
) : ForecastInteractor {

    override suspend fun invoke(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int,
    ): Flow<ResourceResult<Forecast>> = flow {
        emit(ResourceResult.Loading)
        val response = repository.fetchForecast(
            query = query,
            airQuality = airQuality,
            weatherAlerts = weatherAlerts,
            days = days,
        )
        emit(ResourceResult.Success(response.toDomain()))
    }.catch {
        emit(ResourceResult.Error(it))
    }.flowOn(Dispatchers.IO)
}
