package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun VerticalSlider(
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

@Composable
fun SleepQualityThumb() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(
                color = Colors.Orange40,
                shape = Dimens.Shape.Circle
            ).border(4.dp, color = Colors.Orange40Alpha25, shape = Dimens.Shape.Circle),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Icon(
            painter = painterResource(Drawables.Icons.Outlined.Resize),
            contentDescription = null,
            modifier = Modifier.size(Dimens.Icon),
            tint = Colors.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepQualityTrack(sliderState: SliderState) {
    Box(
        modifier = Modifier
            .background(color = Colors.Brown20, shape = Dimens.Shape.Circle)
            .height(16.dp).fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .progress(sliderState = sliderState, height = 16.dp)
                .background(color = Colors.Orange50)
        )
    }
}