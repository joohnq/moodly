package com.joohnq.shared_resources.components.draw_scope

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.shared_resources.theme.Colors

fun DrawScope.drawCenterCircle(
    vectorPainter: VectorPainter,
    iconSize: Size = Size(55.5f, 84f)
) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = (size.width / 2 * 0.7f) * 0.5f

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

fun DrawScope.drawCenterCircle(backgroundColor: Color) {
    val center = Offset(size.width / 2, size.height / 2)
    val radius = (size.width / 2 * 0.7f) * 0.5f + 10f

    drawCircle(
        color = backgroundColor,
        radius = radius,
        center = center
    )
}
