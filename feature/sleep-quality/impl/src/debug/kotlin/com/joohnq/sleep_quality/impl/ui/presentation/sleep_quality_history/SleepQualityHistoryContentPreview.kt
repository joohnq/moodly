package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepHistoryContentPreview() {
    SleepQualityHistoryContent(
        state =
            SleepQualityContract.State(
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
            SleepQualityContract.State(
                records =
                    UiState.Success(
                        listOf()
                    )
            )
    )
}
