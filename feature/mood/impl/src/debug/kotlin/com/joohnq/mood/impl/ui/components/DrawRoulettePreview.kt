package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.VectorPainter
import com.joohnq.api.mapper.toDegrees
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.shared_resources.components.drawSlice
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DrawRoulettePreview() {
   Canvas(modifier = Modifier.fillMaxSize()) {
       drawRoulette(
           sliceAngle = 360f / 6,
           rotation = 0f,
           painterResources = listOf(),
           moods = listOf()
       )
   }
}