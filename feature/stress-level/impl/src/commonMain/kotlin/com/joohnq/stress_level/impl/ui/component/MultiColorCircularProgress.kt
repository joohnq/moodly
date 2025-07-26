package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MultiColorCircularProgress(
    modifier: Modifier = Modifier,
    segments: List<Pair<Color, Float>>,
    gapAngle: Float = 4f,
    strokeWidth: Dp = 8.dp
) {
    val totalProgress = segments.sumOf { it.second.toDouble() }.toFloat()
    val totalGaps = gapAngle * segments.size
    val availableAngle = 360f - totalGaps
    val normalizedSegments = segments.map { it.first to (it.second / totalProgress) }

    Canvas(
        modifier =
            modifier
                .size(100.dp)
                .padding(8.dp)
    ) {
        val radius = size.minDimension / 2
        val center = Offset(size.width / 2, size.height / 2)
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)

        var startAngle = -90f

        normalizedSegments.forEach { (color, percentage) ->
            val sweepAngle = availableAngle * percentage

            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = stroke,
                size = Size(radius * 2, radius * 2),
                topLeft = Offset(center.x - radius, center.y - radius)
            )

            startAngle += sweepAngle + gapAngle
        }
    }
}
