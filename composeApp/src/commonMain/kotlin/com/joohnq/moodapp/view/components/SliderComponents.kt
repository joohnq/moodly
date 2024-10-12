package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import org.jetbrains.compose.resources.painterResource

object SliderComponents {
    @Composable
    fun SleepQualityThumb() {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = CustomColors.Orange40,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(CustomDrawables.Icons.Resize),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = CustomColors.White
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SleepQualityTrack(sliderState: SliderState) {
        Box(
            modifier = Modifier
                .background(color = CustomColors.Brown20, shape = CircleShape)
                .height(16.dp).fillMaxWidth().padding(0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .progress(sliderState = sliderState, height = 16.dp)
                    .background(color = CustomColors.Orange50)
            )
        }
    }
}