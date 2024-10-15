package com.joohnq.moodapp.model.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joohnq.moodapp.model.MyDatabase

fun getMyDatabase(context: Context): RoomDatabase.Builder<MyDatabase> {
    val dbFile = context.getDatabasePath("MyDatabase.db")
    return Room.databaseBuilder<MyDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
}

