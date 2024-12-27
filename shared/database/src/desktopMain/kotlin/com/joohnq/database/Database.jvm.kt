package com.joohnq.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class LocalDatabaseInitializer {
    actual fun init(): RoomDatabase.Builder<LocalDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "MyDatabase.db")
        return Room.databaseBuilder<LocalDatabase>(
            name = dbFile.absolutePath,
        )
    }
}