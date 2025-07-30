package com.joohnq.sleep_quality.impl.ui.presentation.history

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepHistoryContentPreview() {
    SleepQualityHistoryContent(
        state =
            SleepQualityHistoryContract.State(
                records =
                    UiState.Success(
                        SleepQualityRecordResource.allSleepQualityRecordResource
                    )
            )
    )
}

@Preview
@Composable
fun SleepHistoryContentEmptyPreview() {
    SleepQualityHistoryContent(
        state =
            SleepQualityHistoryContract.State(
                records =
                    UiState.Success(
                        listOf()
                    )
            )
    )
}
