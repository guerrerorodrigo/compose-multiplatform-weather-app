package com.rodrigoguerrero.myweather.domain.models

sealed class ResourceResult<out T : Any?> {
    object Loading : ResourceResult<Nothing>()
    data class Success<out T : Any?>(val data: T) : ResourceResult<T>()
    data class Error(val throwable: Throwable?) : ResourceResult<Nothing>()
}
