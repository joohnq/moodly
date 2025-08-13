package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joohnq.mood.add.ui.components.drawRouletteWheel

@Preview
@Composable
private fun Preview() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRouletteWheel(
            sliceAngle = 360f / 6,
            rotation = 0f,
            painterResources = listOf(),
            moods = listOf()
        )
    }
}
