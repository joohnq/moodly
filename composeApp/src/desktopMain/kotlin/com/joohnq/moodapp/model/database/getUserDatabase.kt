package com.joohnq.moodapp.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.joohnq.moodapp.model.MyDatabase
import java.io.File

fun getMyDatabase(): RoomDatabase.Builder<MyDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "MyDatabase.db")
    return Room.databaseBuilder<MyDatabase>(
        name = dbFile.absolutePath,
    )
}

