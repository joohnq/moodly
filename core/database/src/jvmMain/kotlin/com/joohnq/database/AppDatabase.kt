package com.joohnq.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabaseBuilder(): AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "app.db")
    return Room
        .databaseBuilder<AppDatabase>(dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
