package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepHistoryUI(
    state: SleepQualityState,
    onEvent: (SleepHistoryEvent) -> Unit = {},
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { records ->
        }
    )
}

@Preview
@Composable
fun SleepHistoryUIPreview() {
    SleepHistoryUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                    SleepQualityRecord(),
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepHistoryUIPreviewEmpty() {
    SleepHistoryUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf()
            )
        ),
    )
}