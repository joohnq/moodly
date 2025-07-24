package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepHistoryPreview() {
    SleepHistory(
        records = SleepQualityRecordResource.allSleepQualityRecordResource,
    )
}

@Preview
@Composable
fun SleepHistoryEmptyPreview() {
    SleepHistory(
        records = listOf(),
    )
}