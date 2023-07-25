package com.rodrigoguerrero.myweather.data.remote.repositories

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.data.remote.models.dtos.CurrentWeatherResponseDto
import com.rodrigoguerrero.myweather.data.remote.models.dtos.ForecastResponseDto
import com.rodrigoguerrero.myweather.data.remote.models.dtos.SearchResultDto
import com.rodrigoguerrero.myweather.data.remote.requests.CurrentWeatherRequest
import com.rodrigoguerrero.myweather.data.remote.requests.ForecastRequest
import com.rodrigoguerrero.myweather.data.remote.requests.SearchRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get

class KtorWeatherRepositoryImpl(
    private val httpClient: HttpClient,
) : WeatherRepository {

    override suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQuality,
    ): CurrentWeatherResponseDto {
        return httpClient
            .get(
                CurrentWeatherRequest(
                    query = query,
                    airQuality = airQuality.value,
                )
            )
            .body()
    }

    override suspend fun fetchForecast(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int,
    ): ForecastResponseDto {
        return httpClient
            .get(
                ForecastRequest(
                    query = query,
                    airQuality = airQuality.value,
                    alerts = weatherAlerts.value,
                    days = days.coerceAtMost(3),
                )
            )
            .body()
    }

    override suspend fun search(query: String): List<SearchResultDto> {
        return httpClient
            .get(SearchRequest(query = query))
            .body()
    }
}
