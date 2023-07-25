package com.rodrigoguerrero.myweather.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    val location: Flow<String>

    suspend fun saveLocation(location: String)
}
