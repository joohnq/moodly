package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens

@Composable
fun StressLevelIndicator(stressLevel: StressLevel) {
    BoxWithConstraints {
        val boxWidth = (maxWidth - 16.dp) / 5
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                val isSelected = i <= stressLevel.level
                val background = if (isSelected) Modifier.background(
                    brush = Brush.linearGradient(StressLevel.getBrushGradient()[i - 1]),
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

@Composable
fun SleepQualityIndicator(sleepQuality: SleepQuality) {
    BoxWithConstraints {
        val boxWidth = (maxWidth - 16.dp) / 5
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                val isSelected = i <= sleepQuality.level
                val modifier = if (isSelected) Modifier else Modifier.border(
                    width = 1.dp,
                    color = Colors.White,
                    shape = Dimens.Shape.Circle
                )
                Box(
                    modifier = modifier
                        .background(
                            color = if (isSelected) Colors.White else Colors.Transparent,
                            shape = Dimens.Shape.Circle
                        )
                        .width(boxWidth)
                        .height(8.dp)
                )
            }
        }
    }
}
