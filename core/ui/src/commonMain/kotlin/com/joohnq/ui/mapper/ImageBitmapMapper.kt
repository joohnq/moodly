package com.joohnq.ui.mapper

import androidx.compose.ui.graphics.ImageBitmap

expect enum class ImageFormat{
    PNG,
    JPEG
}

expect fun ImageBitmap.toByteArray(format: ImageFormat = ImageFormat.PNG): ByteArray