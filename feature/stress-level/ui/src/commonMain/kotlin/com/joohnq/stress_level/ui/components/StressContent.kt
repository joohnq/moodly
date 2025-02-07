package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

@Composable
fun StressContent(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource> = listOf(),
    onEvent: (StressLevelEvent) -> Unit = {}
) {
    val containerColor = Colors.Gray5
    StressTriggersSection(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
    StressInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
    StressHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
}
