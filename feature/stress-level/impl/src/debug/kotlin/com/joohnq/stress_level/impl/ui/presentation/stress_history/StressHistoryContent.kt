package com.joohnq.stress_level.impl.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelState
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressHistoryContentPreview() {
    StressHistoryContent(
        state = StressLevelState(
            records = UiState.Success(
                StressLevelRecordResource.allStressLevelRecordResourcePreview
            )
        ),
        onEvent = {}
    )
}

@Preview
@Composable
fun StressHistoryContentPreviewEmpty() {
    StressHistoryContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf()
            )
        ),
        onEvent = {}
    )
}