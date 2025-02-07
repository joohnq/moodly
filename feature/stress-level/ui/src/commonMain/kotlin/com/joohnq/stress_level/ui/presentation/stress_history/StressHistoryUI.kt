package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressHistoryUI(
    state: StressLevelState,
    onEvent: (StressHistoryEvent) -> Unit,
) {
    state.records.foldComposable(
        onSuccess = { records ->

        }
    )
}

@Preview
@Composable
fun StressHistoryUIPreview() {
    StressHistoryUI(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource()
                )
            )
        ),
        onEvent = {}
    )
}

@Preview
@Composable
fun StressHistoryUIPreviewEmpty() {
    StressHistoryUI(
        state = StressLevelState(
            records = UiState.Success(
                listOf()
            )
        ),
        onEvent = {}
    )
}