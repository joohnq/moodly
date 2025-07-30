package com.joohnq.sleep_quality.impl.ui.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepQualityContentPreview() {
    SleepQualityOverviewContent(
        state =
            SleepQualityOverviewContract.State(
                records =
                    UiState.Success(
                        SleepQualityRecordResource.allSleepQualityRecordResource
                    )
            )
    )
}

@Preview
@Composable
fun SleepQualityContentEmptyPreview() {
    SleepQualityOverviewContent(
        state =
            SleepQualityOverviewContract.State(
                records =
                    UiState.Success(
                        listOf()
                    )
            )
    )
}
