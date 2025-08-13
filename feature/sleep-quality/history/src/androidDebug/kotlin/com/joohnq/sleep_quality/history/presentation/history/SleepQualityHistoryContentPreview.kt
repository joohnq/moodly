package com.joohnq.sleep_quality.history.presentation.history

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContent
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContract
import com.joohnq.sleep_quality.impl.ui.parameter.ListSleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListSleepQualityRecordResourceParameterProvider::class)
    list: List<SleepQualityRecordResource>,
) {
    SleepQualityHistoryContent(
        state =
            SleepQualityHistoryContract.State(
                items = list
            )
    )
}
