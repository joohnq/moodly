package com.joohnq.sleep_quality.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityContract
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onEvent: (SleepQualityContract.Event) -> Unit,
    onIntent: (SleepQualityContract.Intent) -> Unit
) {
    val containerColor = Colors.Gray5
    SleepInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = {
            onEvent(SleepQualityContract.Event.NavigateToAddSleepQuality)
        }
    )
    SleepHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onDelete = { id -> onIntent(SleepQualityContract.Intent.Delete(id)) },
        onSeeMore = {
            onEvent(SleepQualityContract.Event.NavigateToSleepHistory)
        }
    )
}