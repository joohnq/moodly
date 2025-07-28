package com.joohnq.ui.mapper

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.ui.entity.ImageFormat

expect fun ImageBitmap.toByteArray(format: ImageFormat = ImageFormat.PNG): ByteArray
