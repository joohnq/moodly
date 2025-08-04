package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewContract
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityOverviewContract.Event) -> Unit = {},
    onIntent: (SleepQualityOverviewContract.Intent) -> Unit = {},
) {
    SleepInsight(
        modifier = modifier,
        records = records,
        onCreate = {
            onEvent(SleepQualityOverviewContract.Event.OnNavigateToAddSleepQuality)
        }
    )
    SleepHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onIntent(SleepQualityOverviewContract.Intent.Delete(id)) },
        onSeeMore = {
            onEvent(SleepQualityOverviewContract.Event.OnNavigateToSleepHistory)
        }
    )
}
