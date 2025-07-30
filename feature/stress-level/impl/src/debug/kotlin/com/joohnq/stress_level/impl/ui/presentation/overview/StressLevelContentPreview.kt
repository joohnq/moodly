package com.joohnq.stress_level.impl.ui.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressLevelContentPreview() {
    StressLevelOverviewContent(
        state =
            StressLevelOverviewContract.State(
                records =
                    UiState.Success(
                        StressLevelRecordResource.allStressLevelRecordResourcePreview
                    )
            )
    )
}

@Preview
@Composable
fun StressLevelContentEmptyPreview() {
    StressLevelOverviewContent(
        state =
            StressLevelOverviewContract.State(
                records =
                    UiState.Success(
                        listOf()
                    )
            )
    )
}
