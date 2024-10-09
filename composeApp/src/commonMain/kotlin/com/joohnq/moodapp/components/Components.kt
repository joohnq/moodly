@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
import com.joohnq.moodapp.toPositive
import com.joohnq.moodapp.toRadians
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun findIndexInRange(value: Int, ranges: List<Map<Int, Int>>): Int {
    for ((index, range) in ranges.withIndex()) {
        val (start, end) = range.entries.first().toPair()

        if (value in start..end) {
            return index
        }
    }
    return 0
}

@Composable
fun InfiniteCarousel(moods: List<Mood>, selectedMood: Int) {
    var rotation by remember { mutableStateOf(0f) }
    val painterResources: List<VectorPainter> = moods.map {
        rememberVectorPainter(it.imageVector)
    }
    val targetVectorPainter = rememberVectorPainter(Drawables.Mood.TargetVectorPainter)
    val totalSlices = moods.size
    val limitedAngle = 360f * (PI / 180) // 6,283185307179586
    val sliceAngle = limitedAngle / totalSlices // 0,6283185307179586
    var rounds by remember { mutableStateOf(0.0) }
    var roundsDecimals by remember { mutableStateOf(0.0) }
    var currentPosition by remember { mutableStateOf(18) }
    val range = listOf(
        mapOf(0 to 36),
        mapOf(37 to 72),
        mapOf(73 to 108),
        mapOf(109 to 144),
        mapOf(145 to 180),
        mapOf(181 to 216),
        mapOf(217 to 252),
        mapOf(253 to 288),
        mapOf(289 to 324),
        mapOf(325 to 360)
    )

    LaunchedEffect(currentPosition) {
        val index = findIndexInRange(currentPosition, range)
        printLn {
            printLn("currentPosition $currentPosition")
//            printLn("index ${index + 2}")
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .scale(1.5f)
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    if (dragAmount.x < 0) {
                        rotation += dragAmount.x * 0.005f
                        printLn("rotation+ $rotation")
                        rounds = rotation / limitedAngle.toPositive()
                        printLn("rounds+ $rounds")
                        roundsDecimals = rounds - rounds.toInt()
                        printLn("roundsDecimals+ $roundsDecimals")
                        currentPosition =
                            (((roundsDecimals * 10) * 36) + 18).toInt().coerceIn(0, 360)
                        printLn("-----------")
                    } else {
                        rotation += dragAmount.x.toPositive() * -0.005f
                        printLn("rotation- $rotation")
                        rounds = rotation / limitedAngle
                        printLn("rounds- $rounds")
                        roundsDecimals = rounds - rounds.toInt()
                        printLn("roundsDecimals- $roundsDecimals")
                        currentPosition =
                            (360 - (((roundsDecimals * 10) * 36) - 18)).toInt().coerceIn(0, 360)
                        printLn("currentPosition $currentPosition")
                        printLn("-----------")

                        //Se for ficando negativo que tem que aumentar 0 - 360
                        // Se for ficando positivo tem que diminuir 360 - 0
                        //Tem q começar com 18 graus e começar no index 2
                    }
                }
            }
    ) {
        drawPizza(
            sliceAngle.toFloat(),
            rotation,
            painterResources + painterResources,
            moods
        )
        drawCenterCircle(targetVectorPainter)
    }
}

fun DrawScope.drawPizza(
    sliceAngle: Float,
    rotation: Float,
    painterResources: List<VectorPainter>,
    moods: List<Mood>
) {
    for (i in moods.indices) {
        val startAngle = rotation + sliceAngle * i
//        printLn("startAngle$startAngle")
//        printLn("----------")
        val mood = moods[i]
        drawSlice(
            color = mood.backgroundColor,
            startAngle = toDegrees(startAngle.toDouble()).toFloat(),
            sweepAngle = toDegrees(sliceAngle.toDouble()).toFloat(),
            useCenter = true,
            topLeft = Offset(0f, 0f),
            size = size,
            vectorPainter = painterResources[i]
        )
    }
}

fun DrawScope.drawSlice(
    color: Color,
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
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = useCenter,
        topLeft = topLeft,
        size = size,
    )
    drawIntoCanvas {
        translate(left = iconX, top = iconY - 20f) {
            with(vectorPainter) {
                draw(iconSize)
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