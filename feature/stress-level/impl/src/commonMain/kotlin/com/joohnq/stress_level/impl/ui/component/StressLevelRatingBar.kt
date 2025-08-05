package com.joohnq.stress_level.impl.ui.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens

@Composable
fun StressLevelRatingBar(
    level: Int,
    brushGradient: (Int) -> List<Color>,
) {
    BoxWithConstraints {
        val boxWidth = (maxWidth - 16.dp) / 5
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 1..5) {
                val isSelected = i <= level
                val background =
                    if (isSelected) {
                        Modifier.background(
                            brush = Brush.linearGradient(brushGradient(i - 1)),
                            shape = Dimens.Shape.Circle
                        )
                    } else {
                        Modifier.background(
                            color = Colors.Brown20,
                            shape = Dimens.Shape.Circle
                        )
                    }
                Box(
                    modifier = Modifier.width(boxWidth).height(6.dp).then(background)
                )
            }
        }
    }
}
