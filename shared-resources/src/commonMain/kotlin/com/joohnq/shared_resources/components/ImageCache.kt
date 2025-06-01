package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberPainter
import okio.FileSystem

@Composable
fun ImageCache(directory: String, fileName: String) {
    val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY
    val painter = rememberPainter("${systemTemporaryPath / directory / fileName}")

    AvatarImage(painter)
}
