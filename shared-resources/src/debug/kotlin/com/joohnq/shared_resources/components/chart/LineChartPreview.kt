package com.joohnq.shared_resources.components.chart

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LineChartPreview() {
    LineChart(
        color = Colors.Brown80,
        values =
            listOf(
                0.0,
                1.0,
                2.0,
                3.0,
                4.0,
                5.0
            )
    )
}
