package com.joohnq.sleep_quality.overview.component

import androidx.compose.runtime.Composable
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
    SleepQualityOverviewPanel(
        item = item
    )
}