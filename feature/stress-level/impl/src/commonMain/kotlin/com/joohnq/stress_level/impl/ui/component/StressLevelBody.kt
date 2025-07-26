package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelBody(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource>,
    onAction: (StressLevelContract.Intent) -> Unit = {},
    onEvent: (StressLevelContract.Event) -> Unit = {},
) {
    StressTriggersSection(
        modifier = modifier,
        records = records,
        onAddStressLevel = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
    )
    StressInsight(
        modifier = modifier,
        records = records,
        onCreate = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
    )
    StressHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onAction(StressLevelContract.Intent.Delete(id)) },
        onAddStressLevel = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
    )
}
