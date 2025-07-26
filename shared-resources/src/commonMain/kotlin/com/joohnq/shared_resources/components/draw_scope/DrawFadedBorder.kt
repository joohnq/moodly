package com.joohnq.shared_resources.components.draw_scope

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawFadedBorder(
    color: Color,
    borderWidth: Float,
) {
    val gradientBrush =
        Brush.verticalGradient(
            colors = listOf(color, Color.Transparent),
            startY = 0f,
            endY = size.height
        )

    drawCircle(
        brush = gradientBrush,
        style = Stroke(width = borderWidth),
        radius = (size.minDimension / 2) - (borderWidth / 2)
    )
}
