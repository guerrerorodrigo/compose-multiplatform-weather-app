package com.rodrigoguerrero.myweather.domain.interactors.search

import com.rodrigoguerrero.myweather.data.remote.repositories.WeatherRepository
import com.rodrigoguerrero.myweather.domain.models.ResourceResult
import com.rodrigoguerrero.myweather.domain.models.SearchResult
import com.rodrigoguerrero.myweather.domain.models.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class SearchInteractorImpl(
    private val repository: WeatherRepository,
) : SearchInteractor {

    override suspend fun invoke(
        query: String,
    ): Flow<ResourceResult<List<SearchResult>>> = flow {
        emit(ResourceResult.Loading)
        val response = repository.search(query = query)
        emit(ResourceResult.Success(response.map { it.toDomain() }))
    }.catch {
        emit(ResourceResult.Error(it))
    }.flowOn(Dispatchers.IO)
}
