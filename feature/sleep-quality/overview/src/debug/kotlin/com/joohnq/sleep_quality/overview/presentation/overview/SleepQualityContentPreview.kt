package com.joohnq.sleep_quality.overview.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.parameter.ListSleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.overview.presentation.SleepQualityOverviewContent
import com.joohnq.sleep_quality.overview.presentation.SleepQualityOverviewContract
import com.joohnq.ui.entity.UiState
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
                records = UiState.Success(list)
            )
    )
}
