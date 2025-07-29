package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.parameter.StressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun StressLevelHistoryCardPreview(
    @PreviewParameter(StressLevelRecordResourceParameterProvider::class)
    item: StressLevelRecordResource,
) {
    StressLevelHistoryCard(
        record = item
    )
}
