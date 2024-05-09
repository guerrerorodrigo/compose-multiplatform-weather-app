package com.rodrigoguerrero.myweather.data.local.db.room

import androidx.room.RoomDatabase

interface DatabaseBuilderProvider {
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}
