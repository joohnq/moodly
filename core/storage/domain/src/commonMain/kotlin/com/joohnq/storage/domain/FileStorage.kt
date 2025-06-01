package com.joohnq.storage.domain

import androidx.compose.ui.graphics.ImageBitmap

interface FileStorage {
    suspend fun saveImage(directory: String, fileName: String, data: ImageBitmap): String
    suspend fun readImage(directory: String, fileName: String): ByteArray?
}