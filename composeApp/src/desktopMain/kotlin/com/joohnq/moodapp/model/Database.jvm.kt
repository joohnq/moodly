package com.joohnq.moodapp.model

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class MyDatabaseInitializer {
    actual fun init(): RoomDatabase.Builder<MyDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "MyDatabase.db")
        return Room.databaseBuilder<MyDatabase>(
            name = dbFile.absolutePath,
        )
    }
}