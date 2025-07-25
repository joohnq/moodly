package com.joohnq.shared_resources.components.chart

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties

@Composable
fun LineChart(
    color: Color,
    values: List<Double>,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val maxWidth = maxWidth
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()).fillMaxWidth()
        ) {
            LineChart(
                indicatorProperties = HorizontalIndicatorProperties(
                    false,
                    padding = 0.dp
                ),
                dividerProperties = DividerProperties(false),
                labelHelperProperties = LabelHelperProperties(false),
                dotsProperties = DotProperties(false),
                maxValue = 100.00,
                minValue = 0.0,
                popupProperties = PopupProperties(false),
                gridProperties = GridProperties(
                    false,
                    xAxisProperties = GridProperties.AxisProperties(false),
                    yAxisProperties = GridProperties.AxisProperties(false)
                ),
                labelProperties = LabelProperties(false),
                modifier = Modifier.fillMaxWidth().width(maxWidth).height(120.dp),
                data = remember {
                    listOf(
                        Line(
                            label = "",
                            curvedEdges = true,
                            values = values,
                            color = SolidColor(color),
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 3.dp),
                            firstGradientFillColor = color.copy(
                                alpha = .3f
                            ),
                            secondGradientFillColor = Colors.Transparent,
                        )
                    )
                },
                animationDelay = 0L,
                animationMode = AnimationMode.Together(delayBuilder = { it * 500L }
                ),
            )
        }
    }
}