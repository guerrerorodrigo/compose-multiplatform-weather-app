package com.rodrigoguerrero.myweather.data.local.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM location ORDER BY createdAt DESC")
    fun getLocations(): Flow<List<LocationEntity>>

    @Insert
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Query("DELETE FROM location WHERE id = :id")
    suspend fun deleteLocation(id: Int)

    @Query("SELECT * FROM location WHERE location = :location")
    fun getLocationByName(location: String): Flow<LocationEntity?>
}
