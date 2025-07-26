package com.joohnq.storage.impl

import com.joohnq.storage.api.FileStorage

expect class FileStorageImpl : FileStorage {
    override suspend fun saveImage(
        directory: String,
        fileName: String,
        data: ByteArray,
    ): String

    override suspend fun readImage(
        directory: String,
        fileName: String,
    ): ByteArray?
}
