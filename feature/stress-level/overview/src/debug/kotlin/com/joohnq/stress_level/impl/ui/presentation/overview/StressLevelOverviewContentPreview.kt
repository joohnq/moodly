package com.joohnq.stress_level.impl.ui.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContent
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContract
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    list: List<StressLevelRecordResource>,
) {
    StressLevelOverviewContent(
        state =
            StressLevelOverviewContract.State(
                records =
                    UiState.Success(list)
            )
    )
}
