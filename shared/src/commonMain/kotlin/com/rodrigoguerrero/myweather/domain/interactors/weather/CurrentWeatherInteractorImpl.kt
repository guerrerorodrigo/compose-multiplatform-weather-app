package com.rodrigoguerrero.myweather.domain.interactors.weather

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.repositories.WeatherRepository
import com.rodrigoguerrero.myweather.domain.models.CurrentWeather
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.domain.models.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class CurrentWeatherInteractorImpl(
    private val repository: WeatherRepository,
) : CurrentWeatherInteractor {

    override suspend fun invoke(
        query: String,
        airQuality: AirQuality,
    ): Flow<ResourceResult<CurrentWeather>> = flow {
        emit(ResourceResult.Loading)
        val response = repository.fetchCurrentWeather(
            query = query,
            airQuality = airQuality
        )
        emit(ResourceResult.Success(response.toDomain()))
    }.catch {
        emit(ResourceResult.Error(it))
    }.flowOn(Dispatchers.IO)
}
