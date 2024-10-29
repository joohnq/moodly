package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.view.constants.Colors
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodComponent(modifier: Modifier = Modifier, weekMoods: List<StatsRecord?>) {
    if (weekMoods.size > 7) return

    val last = weekMoods.size - 1

    Column(
        modifier = modifier.size(120.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        weekMoods[last]?.mood?.text?.let {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = stringResource(it),
                    style = TextStyles.MoodComponentText()
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            weekMoods.forEach { statsRecord ->
                val healthLevel = statsRecord?.mood?.healthLevel ?: 10
                val rate: Int = healthLevel.div(10)
                val height = 10.dp * rate
                Box(
                    modifier = Modifier.width(10.dp).height(height)
                        .background(color = Colors.Orange10, shape = CircleShape)
                )
            }
        }
    }
}