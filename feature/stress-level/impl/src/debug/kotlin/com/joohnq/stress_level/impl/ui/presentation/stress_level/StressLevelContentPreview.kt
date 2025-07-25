package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressLevelContentPreview() {
    StressLevelContent(
        state = StressLevelContract.State(
            records = UiState.Success(
                StressLevelRecordResource.allStressLevelRecordResourcePreview
            )
        ),
    )
}

@Preview
@Composable
fun StressLevelContentEmptyPreview() {
    StressLevelContent(
        state = StressLevelContract.State(
            records = UiState.Success(
                listOf()
            )
        ),
    )
}