package com.joohnq.shared_resources

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.joohnq.api.entity.CurvedCanvasPosition

class ConvexCanvas(
    private val offset: Dp,
    private val position: CurvedCanvasPosition
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val offsetPx = with(density) { offset.toPx() }
        val path =
            Path().apply {
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
