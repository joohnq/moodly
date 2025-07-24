package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepQualityHistoryCardWorstPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource.sleepQualityRecordWorstPreview,
    )
}

@Preview
@Composable
fun SleepQualityHistoryCardPoorPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource.sleepQualityRecordPoorPreview,
    )
}

@Preview
@Composable
fun SleepQualityHistoryCardFairPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource.sleepQualityRecordFairPreview,
    )
}

@Preview
@Composable
fun SleepQualityHistoryCardGoodPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource.sleepQualityRecordGoodPreview,
    )
}

@Preview
@Composable
fun SleepQualityHistoryCardExcellentPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
    )
}