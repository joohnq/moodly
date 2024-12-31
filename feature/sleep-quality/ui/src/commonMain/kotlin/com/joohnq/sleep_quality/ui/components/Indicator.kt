package com.joohnq.sleep_quality.ui.components

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
import androidx.compose.ui.unit.dp
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Dimens
import com.joohnq.sleep_quality.ui.SleepQualityResource

@Composable
fun SleepQualityIndicator(sleepQuality: SleepQualityResource) {
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
