package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.MoodResource
import com.joohnq.mood.ui.components.MoodFace
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetricsMoodComponent(modifier: Modifier = Modifier, mood: MoodResource?) {
    Column(
        modifier = modifier.size(120.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (mood == null) {
            Text("Null")
        } else {
            mood.text.let {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    Text(
                        text = stringResource(it),
                        style = TextStyles.Text2xlExtraBold(),
                        color = Colors.White
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                MoodFace(
                    modifier = Modifier.size(120.dp),
                    mood = mood,
                    tint = Colors.White
                )
            }
        }
    }
}