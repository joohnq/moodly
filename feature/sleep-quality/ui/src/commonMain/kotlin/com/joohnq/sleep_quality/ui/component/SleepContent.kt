package com.joohnq.sleep_quality.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityEvent) -> Unit
) {
    val containerColor = Colors.Gray5
    SleepInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = {
            onEvent(SleepQualityEvent.OnNavigateToAddSleepQuality)
        }
    )
    SleepHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onSeeMore = {
            onEvent(SleepQualityEvent.OnNavigateToSleepHistory)
        }
    )
}