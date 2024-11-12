package com.joohnq.moodapp.view.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.mappers.distanceTo
import com.joohnq.moodapp.view.ui.Colors
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun CurvedConnectedDotsRow(
    numberOfDots: Int = 5,
    dotSize: Dp = 12.dp,
    curveHeight: Dp = 40.dp,
    dotColor: Color = Color.White,
    lineColor: Color = dotColor.copy(alpha = 0.5f),
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onDotClick: (Int) -> Unit = {},
) {
    var circlePositions by remember { mutableStateOf(listOf<Offset>()) }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    circlePositions.forEachIndexed { index, center ->
                        val radius = (dotSize.toPx() + 40) / 2
                        if (tapOffset.distanceTo(center) <= radius) {
                            onDotClick(index)
                        }
                    }
                }
            }
    ) {
        val dotSizePx = dotSize.toPx()
        val curveHeightPx = curveHeight.toPx()
        val width = size.width
        val spacing = width / (numberOfDots - 1)

        val positions = mutableListOf<Offset>()

        val path = Path()
        for (i in 0 until numberOfDots - 1) {
            val startX = i * spacing
            val startY =
                size.height / 2 + (curveHeightPx * sin(i * PI / (numberOfDots - 1))).toFloat()
            val endX = (i + 1) * spacing
            val endY =
                size.height / 2 + (curveHeightPx * sin((i + 1) * PI / (numberOfDots - 1))).toFloat()

            if (i == 0) {
                path.moveTo(startX, startY)
            }
            path.lineTo(endX, endY)
        }

        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 10.dp.toPx())
        )

        for (i in 0 until numberOfDots) {
            val x = i * spacing
            val y =
                size.height / 2 + (curveHeightPx * sin(i * PI / (numberOfDots - 1))).toFloat()
            val center = Offset(x, y)

            positions.add(center)

            drawIntoCanvas {
                drawCircle(
                    color = backgroundColor,
                    radius = (dotSizePx + 20) / 2,
                    center = center
                )

                drawCircle(
                    color = dotColor,
                    radius = dotSizePx / 2,
                    center = center
                )

                drawCircle(
                    color = backgroundColor,
                    radius = 4.dp.toPx(),
                    center = center
                )
            }
        }

        circlePositions = positions
    }
}

@Preview
@Composable
fun CurvedConnectedDotsRowPreview() {
    CurvedConnectedDotsRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
        dotColor = Colors.Brown80,
        backgroundColor = Colors.White,
        numberOfDots = 5,
        dotSize = 36.dp,
        curveHeight = 60.dp
    )
}