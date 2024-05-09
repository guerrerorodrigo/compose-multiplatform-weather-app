package com.rodrigoguerrero.myweather.data.local.db.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}

internal const val dbFileName = "location.db"
