package com.joohnq.mood.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.mood.ui.MoodResource
import com.joohnq.shared.ui.components.drawSlice
import com.joohnq.shared.ui.util.mappers.toDegrees

fun DrawScope.drawRoulette(
    sliceAngle: Float,
    rotation: Float,
    painterResources: List<VectorPainter>,
    moods: List<MoodResource>,
) {
    for (i in moods.indices) {
        val startAngle = rotation + sliceAngle * i
        val mood = moods[i]
        drawSlice(
            color = mood.palette.faceColor,
            backgroundColor = mood.palette.faceBackgroundColor,
            startAngle = startAngle.toDegrees().toFloat(),
            sweepAngle = sliceAngle.toDegrees().toFloat(),
            useCenter = true,
            topLeft = Offset(0f, 0f),
            size = size,
            vectorPainter = painterResources[i]
        )
    }
}
