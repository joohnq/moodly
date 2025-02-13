package com.joohnq.storage.data

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.domain.mapper.toByteArray
import com.joohnq.storage.domain.FileStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path.Companion.toPath

actual class FileStorageImpl : FileStorage {
    private val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY

    actual override suspend fun saveImage(
        directory: String,
        fileName: String,
        data: ImageBitmap,
    ): String = withContext(Dispatchers.IO) {
        val basePath = "${systemTemporaryPath / directory}".toPath()
        val path = "${basePath / fileName}".toPath()

        if (!FileSystem.SYSTEM.exists(basePath)) {
            FileSystem.SYSTEM.createDirectory(basePath)
        }

        FileSystem.SYSTEM.write(path) {
            write(data.toByteArray())
        }

        path.toString()
    }

    actual override suspend fun readImage(
        directory: String,
        fileName: String,
    ): ByteArray? = withContext(Dispatchers.IO) {
        val basePath = "${systemTemporaryPath / directory}".toPath()
        val path = "${basePath / fileName}".toPath()

        var imageByteArray = ByteArray(0)
        FileSystem.SYSTEM.read(path) {
            imageByteArray = readByteArray()
        }

        imageByteArray
    }
}