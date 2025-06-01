package com.joohnq.permission

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
}