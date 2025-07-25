package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.api.mapper.organizeMoodRange
import com.joohnq.shared_resources.components.chart.LineChart
import com.joohnq.stress_level.api.mapper.toPercent
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelChart(
    records: List<StressLevelRecordResource>
) {
    val first = records.last().stressLevel
    val levels = records.map { it.stressLevel.level.toPercent() }
    val values = levels.organizeMoodRange()

    LineChart(
        color = first.palette.color,
        values = values
    )
}