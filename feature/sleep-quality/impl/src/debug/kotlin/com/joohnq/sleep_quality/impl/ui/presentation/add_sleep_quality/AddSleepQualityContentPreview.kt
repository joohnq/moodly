package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityState
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddSleepQualityContentWorstPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordWorstPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentPoorPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordPoorPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentFairPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordFairPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentGoodPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordGoodPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentExcellentPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentShowStartTimePickerPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
            showStartTimePickerDialog = true,
            showEndTimePickerDialog = false
        ),
    )
}

@Preview
@Composable
fun AddSleepQualityContentShowEndTimePickerPreview() {
    AddSleepQualityContent(
        state = AddSleepQualityState(
            record = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
            showStartTimePickerDialog = false,
            showEndTimePickerDialog = true
        ),
    )
}