package com.joohnq.mood.ui.components

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
import com.joohnq.mood.ui.mapper.getAllMoodResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.components.drawCenterCircle
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun RouletteMoods(modifier: Modifier = Modifier, setSelectedMood: (MoodResource) -> Unit) {
    val moods = rememberSaveable {
        getAllMoodResource() + getAllMoodResource()
    }
    val painterResources: List<VectorPainter> =
        moods.map { rememberVectorPainter(it.assets.imageVector) }
    val targetVectorPainter = rememberVectorPainter(Drawables.Icons.Filled.RouletteTarget)
    val totalSlices = moods.size
    val limitedAngle = 360f * (PI / 180) // 6,283185307179586
    val limitedAngleForSlice =
        limitedAngle / totalSlices // 0,6283185307179586 / 0,3141592653589793(HALF)
    val aQuarter = (limitedAngle / 4 - limitedAngleForSlice / 2).toFloat()
    var rotation by rememberSaveable { mutableStateOf(0f) }


    Canvas(
        modifier = modifier
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
        drawCenterCircle(backgroundColor = Colors.Alpha15)
        drawCenterCircle(
            vectorPainter = targetVectorPainter,
        )
    }
}
