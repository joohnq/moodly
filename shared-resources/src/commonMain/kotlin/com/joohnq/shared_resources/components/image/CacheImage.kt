package com.joohnq.shared_resources.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.remember.rememberPainter
import okio.FileSystem

@Composable
fun CacheImage(
    modifier: Modifier = Modifier,
    directory: String,
    fileName: String,
) {
    val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY
    val painter = rememberPainter("${systemTemporaryPath / directory / fileName}")

    ProfileImage(
        modifier = modifier,
        painter = painter
    )
}