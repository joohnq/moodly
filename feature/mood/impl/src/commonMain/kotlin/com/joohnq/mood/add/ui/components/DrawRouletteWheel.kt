package com.joohnq.mood.add.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.api.mapper.FloatMapper.toDegrees
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.shared_resources.components.draw_scope.drawSlice

fun DrawScope.drawRouletteWheel(
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
