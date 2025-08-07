package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.overview.component.StressLevelOverviewTriggers
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    list: List<StressLevelRecordResource>,
) {
    StressLevelOverviewTriggers(
        items = list
    )
}
