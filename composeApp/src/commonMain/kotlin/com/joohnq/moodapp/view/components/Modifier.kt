package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SliderState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.progress(
    sliderState: SliderState,
    height: Dp,
    shape: Shape = CircleShape
) = this
    // Compute the fraction based on the slider's current value.
    // We do this by dividing the current value by the total value.
    // However, the start value might not always be 0, so we need to
    // subtract the start value from both the current value and the total value.
    .fillMaxWidth(fraction = (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start))
    .heightIn(min = height)
    .clip(shape)