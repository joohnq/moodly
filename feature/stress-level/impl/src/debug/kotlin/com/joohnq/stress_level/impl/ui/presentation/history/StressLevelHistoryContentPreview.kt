package com.joohnq.stress_level.impl.ui.presentation.history

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewContract
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressLevelHistoryContentPreview() {
    StressLevelHistoryContent(
        state =
            StressLevelOverviewContract.State(
                records =
                    UiState.Success(
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
        state =
            StressLevelOverviewContract.State(
                records =
                    UiState.Success(
                        listOf()
                    )
            ),
        onEvent = {}
    )
}
