package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.stress_level.ui.StressLevelResource

@Composable
fun StressLevelIndicator(stressLevel: StressLevelResource) {
    BoxWithConstraints {
        val boxWidth = (maxWidth - 16.dp) / 5
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                val isSelected = i <= stressLevel.level
                val background = if (isSelected) Modifier.background(
                    brush = Brush.linearGradient(StressLevelResource.getBrushGradient()[i - 1]),
                    shape = Dimens.Shape.Circle
                )
                else Modifier.background(
                    color = Colors.Brown20, shape = Dimens.Shape.Circle
                )
                Box(
                    modifier = Modifier.width(boxWidth).height(6.dp).then(background)
                )
            }
        }
    }
}
