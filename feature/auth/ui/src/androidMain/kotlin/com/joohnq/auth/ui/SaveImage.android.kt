package com.joohnq.auth.ui

import okio.FileSystem
import okio.Path.Companion.toPath

val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY

actual fun saveImage(directory: String, fileName: String, data: ByteArray): String {
    val basePath = "${systemTemporaryPath / directory}".toPath()
    val path = "${basePath / fileName}".toPath()

    if (!FileSystem.SYSTEM.exists(basePath)) {
        FileSystem.SYSTEM.createDirectory(basePath)
    }

    FileSystem.SYSTEM.write(path) {
        write(data)
    }

    return path.toString()
}