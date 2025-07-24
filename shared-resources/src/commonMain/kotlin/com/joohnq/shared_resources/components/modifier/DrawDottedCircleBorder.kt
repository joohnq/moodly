package com.joohnq.shared_resources.components.modifier

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawDottedCircleBorder(
    color: Color,
    radius: Float,
    dotRadius: Float,
    numberOfDots: Int
) {
    val centerX = size.width / 2
    val centerY = size.height / 2

    for (i in 0 until numberOfDots) {
        val angle = (2 * PI * i) / numberOfDots
        val x = centerX + radius * cos(angle).toFloat()
        val y = centerY + radius * sin(angle).toFloat()

        drawCircle(
            color = color,
            radius = dotRadius,
            center = Offset(x, y)
        )
    }
}