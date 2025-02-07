package com.joohnq.sleep_quality.ui.presentation.sleep_quality_history

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.sleep_quality.ui.presentation.sleep_quality_history.event.SleepQualityHistoryEvent
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityHistoryUI(
    state: SleepQualityState,
    onEvent: (SleepQualityHistoryEvent) -> Unit = {},
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { records ->
        }
    )
}

@Preview
@Composable
fun SleepHistoryUIPreview() {
    SleepQualityHistoryUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                    SleepQualityRecordResource(),
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepHistoryUIPreviewEmpty() {
    SleepQualityHistoryUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf()
            )
        ),
    )
}