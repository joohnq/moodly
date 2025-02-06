package com.joohnq.stress_level.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent

@Composable
fun StressContent(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecord> = listOf(),
    onEvent: (StressLevelEvent) -> Unit = {}
) {
    val containerColor = Colors.Gray5
    StressTriggersSection(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
    StressItems(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
}