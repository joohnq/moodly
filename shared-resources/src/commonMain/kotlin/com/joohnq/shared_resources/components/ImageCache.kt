package com.joohnq.shared_resources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.rememberPainter
import com.joohnq.shared_resources.theme.Dimens
import okio.FileSystem

@Composable
fun ImageCache(directory: String, fileName: String) {
    val systemTemporaryPath = FileSystem.SYSTEM_TEMPORARY_DIRECTORY
    val painter = rememberPainter("${systemTemporaryPath / directory / fileName}")

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(64.dp).clip(Dimens.Shape.Circle),
        contentScale = ContentScale.Crop,
    )
}