package com.joohnq.sleep_quality.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.parameter.ListSleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun Preview(
    @PreviewParameter(ListSleepQualityRecordResourceParameterProvider::class)
    list: List<SleepQualityRecordResource>,
) {
    SleepQualityOverviewContent(
        state =
            SleepQualityOverviewContract.State(
                items = list
            )
    )
}
