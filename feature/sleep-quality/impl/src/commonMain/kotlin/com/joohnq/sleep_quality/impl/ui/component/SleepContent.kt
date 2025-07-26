package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityContract.Event) -> Unit = {},
    onAction: (SleepQualityContract.Intent) -> Unit = {},
) {
    SleepInsight(
        modifier = modifier,
        records = records,
        onCreate = {
            onEvent(SleepQualityContract.Event.OnNavigateToAddSleepQuality)
        }
    )
    SleepHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onAction(SleepQualityContract.Intent.Delete(id)) },
        onSeeMore = {
            onEvent(SleepQualityContract.Event.OnNavigateToSleepHistory)
        }
    )
}
