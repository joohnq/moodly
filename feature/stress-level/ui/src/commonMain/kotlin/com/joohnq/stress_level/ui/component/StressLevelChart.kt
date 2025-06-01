package com.joohnq.stress_level.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.domain.mapper.organizeMoodRange
import com.joohnq.shared_resources.components.LineChart
import com.joohnq.stress_level.domain.mapper.toPercent
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelChart(records: List<StressLevelRecordResource>) {
    val first = records.last().stressLevel
    val levels = records.map { it.stressLevel.level.toPercent() }
    val values = levels.organizeMoodRange()
    LineChart(
        color = first.palette.color,
        values = values
    )
}