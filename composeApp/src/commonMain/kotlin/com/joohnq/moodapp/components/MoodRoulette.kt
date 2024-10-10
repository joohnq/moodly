@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.printLn
import com.joohnq.moodapp.toDegrees
import com.joohnq.moodapp.toRadians
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private fun findIndexInRange(value: Int, ranges: List<Map<Int, Int>>): Int {
    for ((index, range) in ranges.withIndex()) {
        val (start, end) = range.entries.first().toPair()

        if (value in start..end) {
            return index
        }
    }
    return 0
}

@Composable
fun MoodRoulette(moods: List<Mood>, selectedMood: Int, setSelectedMood: (Int) -> Unit) {
    val painterResources: List<VectorPainter> = moods.map { rememberVectorPainter(it.imageVector) }
    val targetVectorPainter = rememberVectorPainter(Drawables.Mood.TargetVectorPainter)
    val totalSlices = moods.size
    val limitedAngle = 360f * (PI / 180) // 6,283185307179586
    val limitedAngleForSlice =
        limitedAngle / totalSlices // 0,6283185307179586 // 0,3141592653589793(HALF)
    val initialRotation by remember { mutableStateOf(((limitedAngle / 4) - (limitedAngleForSlice / 2)).toFloat()) }
    var rotation by remember { mutableStateOf(0f) } // (6,283185307179586 / 4) - (0,6283185307179586 / 2) INITIAL VALUE = 1.2566371 LIKE 0
    var percent by remember { mutableStateOf(0.0) } // (6,283185307179586 / 4) - (0,6283185307179586 / 2) INITIAL VALUE = 1.2566371 LIKE 0
    // 6,283185307179586 - 1,2566371 MAX VALUE = 5,02654820718 // RANGE = 5,02654820718 - 1,2566371 = 3,7699111072
    val sliceAngle = limitedAngle / totalSlices // 0,6283185307179586
    var currentPosition by remember { mutableStateOf(0) }
    var r by remember { mutableStateOf(0f) }
    var currentIndex by remember { mutableStateOf(selectedMood) }

    val range = listOf(
        mapOf(-18 to 18),
        mapOf(19 to 54),
        mapOf(55 to 90),
        mapOf(91 to 126),
        mapOf(127 to 162),
        mapOf(163 to 198),
        mapOf(199 to 234),
        mapOf(235 to 270),
        mapOf(271 to 306),
        mapOf(307 to 342)
    )

    val range2 = mutableListOf<Triple<Float, Float, Float>>()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .scale(1.5f)
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                }) { change, dragAmount ->
                    printLn {
                        rotation += dragAmount.x * 0.005f
                        r = rotation - initialRotation
                        percent = r % limitedAngle
                        val angle =
                            if (percent < 0) (percent * -1 * 360 / limitedAngle) else (360 - (percent * 360 / limitedAngle))
                        currentPosition = angle.toInt()
                            .coerceIn(0, 360)
                        if (percent < 0) (percent * -1 * 360 / limitedAngle).toInt()
                            .coerceIn(0, 360) else (360 - (percent * 360 / limitedAngle))
                        val index = findIndexInRange(currentPosition, range)
                        setSelectedMood(index)
                        currentIndex = index
                    }
                    change.consume()
                }
            }
    ) {
        drawPizza(
            sliceAngle.toFloat(),
            rotation,
            painterResources + painterResources,
            moods
        ) { startAngle ->
            val initialAngle = startAngle - initialRotation
            val endAngle = initialAngle + limitedAngleForSlice
            range2.add(
                Triple(
                    initialAngle,
                    (initialAngle + (limitedAngleForSlice / 2)).toFloat(),
                    endAngle.toFloat()
                )
            )
        }
        drawCenterCircle(radius = 270f, backgroundColor = Colors.Alpha15)
        drawCenterCircle(targetVectorPainter)
    }
}

fun DrawScope.drawPizza(
    sliceAngle: Float,
    rotation: Float,
    painterResources: List<VectorPainter>,
    moods: List<Mood>,
    onAddMood: (Float) -> Unit = {}
) {
    for (i in moods.indices) {
        val startAngle = rotation + sliceAngle * i
        val mood = moods[i]
        drawSlice(
            color = mood.color,
            backgroundColor = mood.backgroundColor,
            startAngle = toDegrees(startAngle.toDouble()).toFloat(),
            sweepAngle = toDegrees(sliceAngle.toDouble()).toFloat(),
            useCenter = true,
            topLeft = Offset(0f, 0f),
            size = size,
            vectorPainter = painterResources[i]
        )
        onAddMood(startAngle)
    }
}

fun DrawScope.drawSlice(
    color: Color,
    backgroundColor: Color,
    startAngle: Float,
    sweepAngle: Float,
    useCenter: Boolean,
    topLeft: Offset,
    size: Size,
    vectorPainter: VectorPainter,
    iconSize: Size = Size(180f, 180f)
) {
    val centerX = topLeft.x + size.width / 2
    val centerY = topLeft.y + size.height / 2
    val radius = size.width / 2 * 0.7
    val angleInRadians = toRadians((startAngle + sweepAngle / 2).toDouble())
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

fun DrawScope.drawCenterCircle(vectorPainter: VectorPainter, iconSize: Size = Size(55.5f, 84f)) {
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
            top = (size.height / 4) - 20f
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
