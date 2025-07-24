package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepPanelWorstPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordWorstPreview
    )
}

@Preview
@Composable
fun SleepPanelPoorPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordPoorPreview
    )
}

@Preview
@Composable
fun SleepPanelFairPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordFairPreview
    )
}

@Preview
@Composable
fun SleepPanelGoodPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordGoodPreview
    )
}

@Preview
@Composable
fun SleepPanelExcellentPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordExcellentPreview
    )
}

@Preview
@Composable
fun SleepPanelWithSleepInfluencesPreview() {
    SleepPanel(
        record = SleepQualityRecordResource.sleepQualityRecordWithSleepInfluencesPreview
    )
}