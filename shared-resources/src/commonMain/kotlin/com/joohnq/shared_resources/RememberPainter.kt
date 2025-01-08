package com.joohnq.shared_resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun rememberPainter(data: String?): Painter