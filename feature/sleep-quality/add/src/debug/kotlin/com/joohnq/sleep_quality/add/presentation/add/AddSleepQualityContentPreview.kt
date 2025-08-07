package com.joohnq.sleep_quality.add.presentation.add

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.add.presentation.AddSleepQualityContent
import com.joohnq.sleep_quality.add.presentation.AddSleepQualityContract
import com.joohnq.sleep_quality.impl.ui.parameter.SleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(SleepQualityRecordResourceParameterProvider::class)
    item: SleepQualityRecordResource,
) {
    AddSleepQualityContent(
        state =
            AddSleepQualityContract.State(
                item = item,
                showStartTimePickerDialog = false,
                showEndTimePickerDialog = false
            )
    )
}

@Preview
@Composable
private fun ShowStartTimePickerDialogPreview() {
    AddSleepQualityContent(
        state =
            AddSleepQualityContract.State(
                item = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
                showStartTimePickerDialog = true,
                showEndTimePickerDialog = false
            )
    )
}

@Preview
@Composable
private fun ShowEndTimePickerPreview() {
    AddSleepQualityContent(
        state =
            AddSleepQualityContract.State(
                item = SleepQualityRecordResource.sleepQualityRecordExcellentPreview,
                showStartTimePickerDialog = false,
                showEndTimePickerDialog = true
            )
    )
}
