package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.impl.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent

@Composable
fun StressContent(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource>,
    onAction: (StressLevelIntent) -> Unit = {},
    onEvent: (StressLevelEvent) -> Unit = {}
) {
    StressTriggersSection(
        modifier = modifier,
        records = records,
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
    StressInsight(
        modifier = modifier,
        records = records,
        onCreate = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
    StressHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onAction(StressLevelIntent.Delete(id)) },
        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
    )
}
