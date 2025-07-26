package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressPanelOnePreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceOnePreview
    )
}

@Preview
@Composable
fun StressPanelTwoPreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceTwoPreview
    )
}

@Preview
@Composable
fun StressPanelThreePreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceThreePreview
    )
}

@Preview
@Composable
fun StressPanelFourPreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceFourPreview
    )
}

@Preview
@Composable
fun StressPanelFivePreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceFivePreview
    )
}

@Preview
@Composable
fun StressPanelWithStressorsPreview() {
    StressPanel(
        record = StressLevelRecordResource.stressLevelRecordResourceWithStressorsPreview
    )
}
