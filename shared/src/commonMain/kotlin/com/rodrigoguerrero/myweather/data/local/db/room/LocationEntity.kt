package com.rodrigoguerrero.myweather.data.local.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val location: String,
    val createdAt: Long,
)
