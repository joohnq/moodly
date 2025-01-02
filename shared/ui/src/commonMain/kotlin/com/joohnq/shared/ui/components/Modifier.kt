package com.joohnq.shared.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SliderState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.shared.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.progress(
    sliderState: SliderState,
    height: Dp,
    shape: Shape = Dimens.Shape.Circle
) = this
    // Compute the fraction based on the slider's current value.
    // We do this by dividing the current value by the total value.
    // However, the start value might not always be 0, so we need to
    // subtract the start value from both the current value and the total value.
    .fillMaxWidth(fraction = (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start))
    .heightIn(min = height)
    .clip(shape)


/**
 * Adds a dashed border around a Composable component.
 *
 * @param color The color of the dashed border.
 * @param strokeWidth The width of the dashed border stroke.
 * @param dashLength The length of each dash in the border.
 * @param gapLength The length of the gap between each dash.
 * @param cap The style of the stroke caps at the ends of dashes.
 *
 * @return A Modifier with the dashed border applied.
 */
fun Modifier.dashedLine(
    color: Color,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = dashedLine(brush = SolidColor(color), strokeWidth, dashLength, gapLength, cap)

/**
 * Adds a dashed border around a Composable component.
 *
 * @param brush The brush of the dashed border.
 * @param strokeWidth The width of the dashed border stroke.
 * @param dashLength The length of each dash in the border.
 * @param gapLength The length of the gap between each dash.
 * @param cap The style of the stroke caps at the ends of dashes.
 *
 * @return A Modifier with the dashed border applied.
 */
fun Modifier.dashedLine(
    brush: Brush,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {

    val pathEffect =
        PathEffect.dashPathEffect(floatArrayOf(dashLength.toPx(), gapLength.toPx()), 0f)

    drawContent()

    drawLine(
        brush = brush,
        start = Offset(0f, 0f),
        end = Offset(size.width, 0f),
        cap = cap,
        pathEffect = pathEffect,
        strokeWidth = strokeWidth.toPx()
    )
}