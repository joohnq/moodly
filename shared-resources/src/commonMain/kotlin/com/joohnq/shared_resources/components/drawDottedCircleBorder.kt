package com.joohnq.shared_resources.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

fun Modifier.drawDottedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = drawDottedBorder(brush = SolidColor(color), shape, strokeWidth, dashLength, gapLength, cap)

fun Modifier.drawDottedBorder(
    brush: Brush,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {

    val outline = shape.createOutline(size, layoutDirection, density = this)

    val dashedStroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    drawContent()

    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = brush
    )
}