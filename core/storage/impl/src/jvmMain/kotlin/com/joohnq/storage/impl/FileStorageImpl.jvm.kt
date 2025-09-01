package com.joohnq.storage.impl

import com.joohnq.storage.api.FileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}
