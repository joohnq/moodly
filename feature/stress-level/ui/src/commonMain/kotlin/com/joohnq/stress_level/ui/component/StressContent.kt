package com.joohnq.stress_level.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelContract
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

@Composable
fun StressContent(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource> = listOf(),
    onIntent: (StressLevelContract.Intent) -> Unit = {},
    onEvent: (StressLevelContract.Event) -> Unit = {}
) {
    val containerColor = Colors.Gray5
    StressTriggersSection(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onAddStressLevel = { onEvent(StressLevelContract.Event.AddStressLevel) },
    )
    StressInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = { onEvent(StressLevelContract.Event.AddStressLevel) },
    )
    StressHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onDelete = { id -> onIntent(StressLevelContract.Intent.Delete(id)) },
        onAddStressLevel = { onEvent(StressLevelContract.Event.AddStressLevel) },
    )
}
