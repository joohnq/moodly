package com.joohnq.moodapp.model.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joohnq.moodapp.model.UserPreferencesDatabase
import kotlinx.coroutines.Dispatchers

fun getUserPreferencesDatabase(context: Context): RoomDatabase.Builder<UserPreferencesDatabase> {
    val dbFile = context.getDatabasePath("UserPreferences.db")
    return Room.databaseBuilder<UserPreferencesDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    ).setQueryCoroutineContext(Dispatchers.IO)
}
