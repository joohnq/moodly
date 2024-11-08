package com.joohnq.moodapp.model

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class MyDatabaseInitializer(
    private val context: Context
) {
    actual fun init(): RoomDatabase.Builder<MyDatabase> {
        val dbFile = context.getDatabasePath("MyDatabase.db")
        return Room.databaseBuilder<MyDatabase>(
            context = context.applicationContext,
            name = dbFile.absolutePath,
        )
    }
}