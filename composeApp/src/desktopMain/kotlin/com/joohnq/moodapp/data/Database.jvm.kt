package com.joohnq.moodapp.data

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class MyDatabaseInitializer {
    actual fun init(): RoomDatabase.Builder<LocalDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "MyDatabase.db")
        return Room.databaseBuilder<LocalDatabase>(
            name = dbFile.absolutePath,
        )
    }
}