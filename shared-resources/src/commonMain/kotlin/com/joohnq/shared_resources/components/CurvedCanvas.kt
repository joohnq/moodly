package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.api.entity.CurvedCanvasPosition

class ConvexCanvas(private val offset: Dp, private val position: CurvedCanvasPosition) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val offsetPx = with(density) { offset.toPx() }
        val path = Path().apply {
            when (position) {
                CurvedCanvasPosition.TOP -> {
                    moveTo(0f, offsetPx)
                    quadraticTo(size.width / 2, -offsetPx, size.width, offsetPx)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                }

                CurvedCanvasPosition.BOTTOM -> {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height - offsetPx)
                    quadraticTo(size.width / 2, size.height + offsetPx, 0f, size.height - offsetPx)
                }
            }
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun ConvexContentLayout(
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
            modifier = Modifier.dpOffset(y = -offset / 2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

