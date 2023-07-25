package com.rodrigoguerrero.myweather.data.remote.repositories

import com.rodrigoguerrero.myweather.data.remote.models.AirQuality
import com.rodrigoguerrero.myweather.data.remote.models.WeatherAlerts
import com.rodrigoguerrero.myweather.data.remote.models.dtos.CurrentWeatherResponseDto
import com.rodrigoguerrero.myweather.data.remote.models.dtos.ForecastResponseDto
import com.rodrigoguerrero.myweather.data.remote.models.dtos.SearchResultDto

interface WeatherRepository {

    suspend fun fetchCurrentWeather(
        query: String,
        airQuality: AirQuality,
    ): CurrentWeatherResponseDto

    suspend fun fetchForecast(
        query: String,
        airQuality: AirQuality,
        weatherAlerts: WeatherAlerts,
        days: Int,
    ): ForecastResponseDto

    suspend fun search(query: String): List<SearchResultDto>
}
