package com.joohnq.sleep_quality.overview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.sleep_quality.impl.ui.component.SleepQualityHistory
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.overview.presentation.SleepQualityOverviewContract

@Composable
fun SleepQualityOverviewBody(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityOverviewContract.Event) -> Unit = {},
    onIntent: (SleepQualityOverviewContract.Intent) -> Unit = {},
) {
    SleepQualityOverviewInsight(
        modifier = modifier,
        records = records,
        onCreate = {
            onEvent(SleepQualityOverviewContract.Event.NavigateToAddSleepQuality)
        }
    )
    SleepQualityHistory(
        modifier = modifier,
        records = records.take(7),
        onDelete = { id -> onIntent(SleepQualityOverviewContract.Intent.Delete(id)) },
        onSeeMore = {
            onEvent(SleepQualityOverviewContract.Event.NavigateToSleepQualityHistory)
        }
    )
}