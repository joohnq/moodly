package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressInsightPreview() {
    StressInsight(
        records = StressLevelRecordResource.allStressLevelRecordResourcePreview
    )
}
