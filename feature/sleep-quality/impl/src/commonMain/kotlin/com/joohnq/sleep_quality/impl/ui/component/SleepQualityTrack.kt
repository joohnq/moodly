package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.progress
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepQualityTrack(
    state: SliderState
) {
    Box(
        modifier = Modifier
            .background(color = Colors.Brown20, shape = Dimens.Shape.Circle)
            .height(16.dp).fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier.Companion
                .progress(sliderState = state, height = 16.dp)
                .background(color = Colors.Orange50)
        )
    }
}