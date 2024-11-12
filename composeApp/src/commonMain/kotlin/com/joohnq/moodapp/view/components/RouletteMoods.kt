package com.joohnq.moodapp.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun RouletteMoods(paddingBottom: Int, setSelectedMood: (Mood) -> Unit) {
    val moods =
        rememberSaveable {
            listOf(
                Mood.Depressed,
                Mood.Sad,
                Mood.Neutral,
                Mood.Happy,
                Mood.Overjoyed,
                Mood.Depressed,
                Mood.Sad,
                Mood.Neutral,
                Mood.Happy,
                Mood.Overjoyed,
            )
        }
    val painterResources: List<VectorPainter> = moods.map { rememberVectorPainter(it.imageVector) }
    val targetVectorPainter = rememberVectorPainter(Drawables.Mood.TargetVectorPainter)
    val totalSlices = moods.size
    val limitedAngle = 360f * (PI / 180) // 6,283185307179586
    val limitedAngleForSlice =
        limitedAngle / totalSlices // 0,6283185307179586 / 0,3141592653589793(HALF)
    val aQuarter = (limitedAngle / 4 - limitedAngleForSlice / 2).toFloat()
    var rotation by rememberSaveable { mutableStateOf(0f) }


    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .scale(1.5f)
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                    val i = rotation % limitedAngle
                    val middle = ((i / limitedAngleForSlice).roundToInt()) * limitedAngleForSlice
                    rotation = middle.toFloat()
                }) { change, dragAmount ->
                    rotation += dragAmount.x * 0.005f
                    val percent = (rotation - aQuarter) % limitedAngle
                    val differenceAngle = percent.absoluteValue * 360 / limitedAngle
                    val angle = if (percent < 0) differenceAngle else 360 - differenceAngle
                    val index = ((angle / 360) * totalSlices).roundToInt() % totalSlices
                    setSelectedMood(moods[index])
                    change.consume()
                }
            }
    ) {
        drawRoulette(
            limitedAngleForSlice.toFloat(),
            rotation,
            painterResources + painterResources,
            moods
        )
        drawCenterCircle(radius = 270f, backgroundColor = Colors.Alpha15)
        drawCenterCircle(targetVectorPainter, -(paddingBottom))
    }
}
