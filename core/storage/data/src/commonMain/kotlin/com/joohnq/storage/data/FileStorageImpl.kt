package com.joohnq.storage.data

import com.joohnq.storage.domain.FileStorage

expect class FileStorageImpl : FileStorage {
    override suspend fun saveImage(directory: String, fileName: String, data: ByteArray): String
    override suspend fun readImage(directory: String, fileName: String): ByteArray?
}