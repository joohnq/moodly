package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressHistoryUI(
    state: StressLevelState,
    onEvent: (StressHistoryEvent) -> Unit,
) {
    state.stressLevelRecords.foldComposable(
        onSuccess = { records ->

        }
    )
}

@Preview
@Composable
fun StressHistoryUIPreview() {
    StressHistoryUI(
        state = StressLevelState(
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord()
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
            stressLevelRecords = UiState.Success(
                listOf(
                )
            )
        ),
        onEvent = {}
    )
}