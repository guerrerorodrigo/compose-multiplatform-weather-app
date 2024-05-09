package com.rodrigoguerrero.myweather.data.local.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.rodrigoguerrero.myweather.data.local.db.room.AppDatabase
import com.rodrigoguerrero.myweather.data.local.db.room.instantiateImpl
import com.rodrigoguerrero.myweather.data.local.db.room.DatabaseBuilderProvider
import com.rodrigoguerrero.myweather.data.local.db.room.dbFileName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

internal class IosDatabaseBuilderProvider : DatabaseBuilderProvider {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/$dbFileName"
        return Room
            .databaseBuilder<AppDatabase>(
                name = dbFilePath,
                factory = { AppDatabase::class.instantiateImpl() },
            ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
    }
}
