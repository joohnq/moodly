package com.joohnq.moodapp.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.joohnq.moodapp.model.UserPreferencesDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getUserPreferencesDatabase(): RoomDatabase.Builder<UserPreferencesDatabase> {
    val dbFile = documentDirectory() + "/UserPreferences.db"

    return Room.databaseBuilder<UserPreferencesDatabase>(
        name = dbFile,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}