package com.joohnq.stress_level.impl.ui.presentation.stress_level_history

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressLevelHistoryContentPreview() {
    StressLevelHistoryContent(
        state = StressLevelContract.State(
            records = UiState.Success(
                StressLevelRecordResource.allStressLevelRecordResourcePreview
            )
        ),
        onEvent = {}
    )
}

@Preview
@Composable
fun StressLevelHistoryContentPreviewEmpty() {
    StressLevelHistoryContent(
        state = StressLevelContract.State(
            records = UiState.Success(
                listOf()
            )
        ),
        onEvent = {}
    )
}