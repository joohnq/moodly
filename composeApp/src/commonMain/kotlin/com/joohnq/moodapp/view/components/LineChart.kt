package com.joohnq.moodapp.view.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.joohnq.moodapp.view.ui.Colors
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties

@Composable
fun StressLevelChart() {
    LineChart(
        indicatorProperties = HorizontalIndicatorProperties(
            false,
            padding = 0.dp
        ),
        dividerProperties = DividerProperties(false),
        labelHelperProperties = LabelHelperProperties(false),
        maxValue = 100.00,
        minValue = 0.0,
//                            dotsProperties = DotProperties(
//                                true,
//                                radius = 6.dp,
//                                color = SolidColor(Colors.Brown80)
//                            ),
        popupProperties = PopupProperties(false),
        gridProperties = GridProperties(
            false,
            xAxisProperties = GridProperties.AxisProperties(false),
            yAxisProperties = GridProperties.AxisProperties(false)
        ),
        labelProperties = LabelProperties(false),
        modifier = Modifier.fillMaxWidth().height(130.dp)
            .width(12 * 10.dp),
        data = remember {
            listOf(
                Line(
                    label = "",
                    curvedEdges = false,
                    values = listOf(
                        0.0,
                        0.0,
                        0.0,
                        50.0,
                        50.0,
                        50.0,
                        25.0,
                        25.0,
                        25.0
                    ),
                    color = SolidColor(Colors.Brown80),
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(width = 3.dp),
                )
            )
        },
        animationDelay = 0L,
        animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
        }
        ),
    )
}