package com.rodrigoguerrero.myweather.data.remote.config

import com.rodrigoguerrero.myweather.data.remote.config.NetworkConstants.WEATHER_API_KEY
import com.rodrigoguerrero.myweather.data.remote.models.RemoteException
import com.rodrigoguerrero.myweather.data.remote.models.RemoteExceptionType
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    expectSuccess = true
    install(Resources)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    exceptionHandling()

    defaultRequest {
        url {
            host = "api.weatherapi.com"
            protocol = URLProtocol.HTTPS
            parameters.append("key", WEATHER_API_KEY)
        }
    }
}

private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.exceptionHandling() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            val type = when (exception) {
                is ClientRequestException -> RemoteExceptionType.CLIENT_ERROR
                is ServerResponseException -> RemoteExceptionType.SERVER_ERROR
                is JsonConvertException -> RemoteExceptionType.PARSING_ERROR
                else -> RemoteExceptionType.UNKNOWN
            }
            throw RemoteException(type)
        }
    }
}
