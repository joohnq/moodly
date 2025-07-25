package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SleepQualityVerticalSlider(
    modifier: Modifier = Modifier,
    sliderValue: Float,
    thumb: @Composable () -> Unit,
    track: @Composable (SliderState) -> Unit,
    sliderColors: SliderColors,
    setSliderValue: (Float) -> Unit,
) {
    Slider(
        value = sliderValue,
        onValueChange = setSliderValue,
        valueRange = 0f..100f,
        steps = 3,
        thumb = { thumb() },
        track = { sliderState ->
            track(sliderState)
        },
        colors = sliderColors,
        modifier = modifier
            .graphicsLayer {
                rotationZ = 270f
                transformOrigin = TransformOrigin(0f, 0f)
            }
            .layout { measurable, constraints ->
                val placeable = measurable.measure(
                    Constraints(
                        minWidth = constraints.minHeight,
                        maxWidth = constraints.maxHeight,
                        minHeight = constraints.minWidth,
                        maxHeight = constraints.maxHeight,
                    )
                )
                layout(placeable.height, placeable.width) {
                    placeable.place(-placeable.width, 0)
                }
            }

    )
}