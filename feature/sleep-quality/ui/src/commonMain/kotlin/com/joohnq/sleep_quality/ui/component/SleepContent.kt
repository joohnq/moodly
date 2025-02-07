package com.joohnq.sleep_quality.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onSeeAll: () -> Unit
) {
    val containerColor = Colors.Gray5
    SleepInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onClick = {}
    )
    SleepHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onClick = {},
        onSeeAll = onSeeAll
    )
}