package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.api.entity.CurvedCanvasPosition
import com.joohnq.shared_resources.ConvexCanvas
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.dpOffset

@Composable
fun ConvexColumnLayout(
    backgroundColor: Color,
    position: CurvedCanvasPosition = CurvedCanvasPosition.TOP,
    offset: Dp = 60.dp,
    spacer: Dp = 30.dp,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = ConvexCanvas(offset = offset, position = position),
                color = backgroundColor
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(spacer)
        Column(
            modifier = Modifier.Companion.dpOffset(y = -offset / 2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}