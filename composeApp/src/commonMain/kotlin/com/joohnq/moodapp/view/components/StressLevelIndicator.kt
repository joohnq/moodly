package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.view.constants.Colors

@Composable
fun StressLevelIndicator(stressLevel: StressLevel) {
    BoxWithConstraints {
        val boxWidth = (maxWidth - 16.dp) / 5
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                val isSelected = i <= stressLevel.level
                val background = if (isSelected) Modifier.background(
                    brush = Brush.linearGradient(StressLevel.getBrushGradient()[i - 1]),
                    shape = CircleShape
                )
                else Modifier.background(
                    color = Colors.Brown20, shape = CircleShape
                )
                Box(
                    modifier = Modifier.width(boxWidth).height(6.dp).then(background)
                )
            }
        }
    }
}