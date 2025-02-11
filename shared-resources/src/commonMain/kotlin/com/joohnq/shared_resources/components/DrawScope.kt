package com.joohnq.shared_resources.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.domain.mapper.toRadians
import com.joohnq.shared_resources.theme.Colors
import kotlin.math.cos
import kotlin.math.sin

fun DrawScope.drawSlice(
    color: Color,
    backgroundColor: Color,
    startAngle: Float,
    sweepAngle: Float,
    useCenter: Boolean,
    topLeft: Offset,
    size: Size,
    vectorPainter: VectorPainter,
    iconSize: Size = Size(180f, 180f),
) {
    val centerX = topLeft.x + size.width / 2
    val centerY = topLeft.y + size.height / 2
    val radius = size.width / 2 * 0.7
    val angleInRadians = (startAngle + sweepAngle / 2).toRadians()
    val iconX = centerX + (radius * cos(angleInRadians)).toFloat() - iconSize.width / 2
    val iconY = centerY + (radius * sin(angleInRadians)).toFloat() - iconSize.height / 2

    drawArc(
        color = backgroundColor,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = useCenter,
        topLeft = topLeft,
        size = size,
    )
    drawIntoCanvas {
        translate(left = iconX, top = iconY - 20f) {
            with(vectorPainter) {
                draw(size = iconSize, colorFilter = ColorFilter.tint(color))
            }
        }
    }
}

fun DrawScope.drawCenterCircle(
    vectorPainter: VectorPainter,
    iconSize: Size = Size(55.5f, 84f),
) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = 250f

    drawCircle(
        color = Colors.Brown10,
        radius = radius,
        center = center
    )

    drawIntoCanvas {
        translate(
            left = center.x - (iconSize.width / 2),
            top = center.y - radius - (iconSize.height / 2)
        ) {
            with(vectorPainter) {
                draw(iconSize)
            }
        }
    }
}

fun DrawScope.drawCenterCircle(radius: Float, backgroundColor: Color) {
    val center = Offset(size.width / 2, size.height / 2)

    drawCircle(
        color = backgroundColor,
        radius = radius,
        center = center
    )
}
