package com.rodrigoguerrero.myweather.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigoguerrero.myweather.data.local.db.room.AppDatabase
import com.rodrigoguerrero.myweather.data.local.db.room.DatabaseBuilderProvider
import com.rodrigoguerrero.myweather.data.local.db.room.dbFileName
import kotlinx.coroutines.Dispatchers

internal class AndroidDatabaseBuilderProvider(
    private val context: Context,
) : DatabaseBuilderProvider {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = context.getDatabasePath(dbFileName)
        return Room
            .databaseBuilder<AppDatabase>(
                context = context,
                name = dbFile.absolutePath,
            )
            .setQueryCoroutineContext(context = Dispatchers.IO)
    }
}
