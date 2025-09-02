package com.joohnq.storage.impl

import com.joohnq.storage.api.FileStorage
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path.Companion.toPath
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class FileStorageImpl : FileStorage {
    private val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY

    actual override suspend fun saveImage(
        directory: String,
        fileName: String,
        data: ByteArray,
    ): String =
        withContext(Dispatchers.IO) {
            val basePath = "${systemTemporaryPath / directory}".toPath()
            val path = "${basePath / fileName}".toPath()

            if (!FileSystem.SYSTEM.exists(basePath)) {
                FileSystem.SYSTEM.createDirectory(basePath)
            }

            FileSystem.SYSTEM.write(path) {
                write(data)
            }

            path.toString()
        }

    actual override suspend fun readImage(
        directory: String,
        fileName: String,
    ): ByteArray? =
        withContext(Dispatchers.IO) {
            val basePath = "${systemTemporaryPath / directory}".toPath()
            val path = "${basePath / fileName}".toPath()

            var imageByteArray = ByteArray(0)
            FileSystem.SYSTEM.read(path) {
                imageByteArray = readByteArray()
            }

            imageByteArray
        }

    @OptIn(ExperimentalForeignApi::class)
    actual override suspend fun deleteDatabase(fileName: String) {
        val fileManager = NSFileManager.defaultManager
        val urls =
            fileManager.URLsForDirectory(
                directory = NSApplicationSupportDirectory,
                inDomains = NSUserDomainMask
            )
        val appSupportDir = urls.first() as? NSURL
        val dbUrl = appSupportDir?.URLByAppendingPathComponent(fileName)
        if (dbUrl != null && fileManager.fileExistsAtPath(dbUrl.path!!)) {
            try {
                fileManager.removeItemAtURL(dbUrl, null)
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}
