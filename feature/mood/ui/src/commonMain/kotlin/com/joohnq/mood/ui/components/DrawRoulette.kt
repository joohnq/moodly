package com.joohnq.mood.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.domain.mapper.toDegrees
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.components.drawSlice
import androidx.compose.ui.geometry.Size

fun DrawScope.drawRoulette(
    sliceAngle: Float,
    rotation: Float,
    painterResources: List<VectorPainter>,
    moods: List<MoodResource>,
) {
    val iconSize = (size.width / 2 * 0.7f) * 0.5f
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
            vectorPainter = painterResources[i],
            iconSize = Size(iconSize, iconSize)
        )
    }
}
