package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityIntent

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityEvent) -> Unit = {},
    onAction: (SleepQualityIntent) -> Unit = {}
) {
    SleepInsight(
        modifier = modifier,
        records = records,
        onCreate = {
            onEvent(SleepQualityEvent.OnNavigateToAddSleepQuality)
        }
    )
    SleepHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onAction(SleepQualityIntent.Delete(id)) },
        onSeeMore = {
            onEvent(SleepQualityEvent.OnNavigateToSleepHistory)
        }
    )
}