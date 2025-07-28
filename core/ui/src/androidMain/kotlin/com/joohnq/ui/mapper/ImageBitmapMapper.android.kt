package com.joohnq.ui.mapper

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.joohnq.ui.entity.ImageFormat
import java.io.ByteArrayOutputStream

actual fun ImageBitmap.toByteArray(format: ImageFormat): ByteArray =
    ByteArrayOutputStream().use {
        asAndroidBitmap().compress(format, 100, it)
        it.toByteArray()
    }
