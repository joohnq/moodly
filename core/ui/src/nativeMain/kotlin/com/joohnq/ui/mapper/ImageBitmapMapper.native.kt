package com.joohnq.ui.mapper

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import com.joohnq.ui.entity.ImageFormat
import org.jetbrains.skia.Image

actual fun ImageBitmap.toByteArray(format: ImageFormat): ByteArray {
    val data =
        Image
            .makeFromBitmap(asSkiaBitmap())
            .encodeToData(format) ?: error("This painter cannot be encoded to $format")

    return data.bytes
}
