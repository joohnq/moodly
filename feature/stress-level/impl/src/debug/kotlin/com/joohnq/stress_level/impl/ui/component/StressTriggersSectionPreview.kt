package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressTriggersSectionPreview() {
    StressTriggersSection(
        records = StressLevelRecordResource.allStressLevelRecordResourcePreview
    )
}

@Preview
@Composable
fun StressTriggersSectionEmptyPreview() {
    StressTriggersSection(
        records = listOf(),
    )
}