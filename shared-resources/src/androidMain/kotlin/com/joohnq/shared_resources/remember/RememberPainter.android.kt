package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil3.compose.rememberAsyncImagePainter

@Composable
actual fun rememberPainter(data: String?): Painter = rememberAsyncImagePainter(data)