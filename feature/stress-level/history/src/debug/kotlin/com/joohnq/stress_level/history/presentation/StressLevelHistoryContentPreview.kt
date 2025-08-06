package com.joohnq.stress_level.history.presentation

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toGroupedByDate
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    list: List<StressLevelRecordResource>,
) {
    StressLevelHistoryContent(
        state =
            StressLevelHistoryContract.State(
                items = list.toGroupedByDate()
            )
    )
}
