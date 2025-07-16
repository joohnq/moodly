package com.joohnq.storage.domain

interface FileStorage {
    suspend fun saveImage(directory: String, fileName: String, data: ByteArray): String
    suspend fun readImage(directory: String, fileName: String): ByteArray?
}