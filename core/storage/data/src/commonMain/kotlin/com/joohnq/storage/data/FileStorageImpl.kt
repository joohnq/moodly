package com.joohnq.storage.data

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.storage.domain.FileStorage

expect class FileStorageImpl : FileStorage {
    override suspend fun saveImage(directory: String, fileName: String, data: ImageBitmap): String
    override suspend fun readImage(directory: String, fileName: String): ByteArray?
}