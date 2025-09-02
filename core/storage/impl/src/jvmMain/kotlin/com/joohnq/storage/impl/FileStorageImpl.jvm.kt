package com.joohnq.storage.impl

import com.joohnq.storage.api.FileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

actual class FileStorageImpl : FileStorage {
    actual override suspend fun saveImage(
        directory: String,
        fileName: String,
        data: ByteArray,
    ): String =
        withContext(Dispatchers.IO) {
            ""
        }

    actual override suspend fun readImage(
        directory: String,
        fileName: String,
    ): ByteArray? =
        withContext(Dispatchers.IO) {
            null
        }

    actual override suspend fun deleteDatabase(fileName: String) {
        val dbFile = File(fileName)
        if (dbFile.exists()) {
            dbFile.delete()
        }
    }
}
