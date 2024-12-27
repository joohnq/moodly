package com.joohnq.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.joohnq.database.LocalDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class LocalDatabaseInitializer {
    actual fun init(): RoomDatabase.Builder<LocalDatabase> {
        val dbFile = documentDirectory() + "/MyDatabase.db"

        return Room.databaseBuilder<LocalDatabase>(
            name = dbFile,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }
}

