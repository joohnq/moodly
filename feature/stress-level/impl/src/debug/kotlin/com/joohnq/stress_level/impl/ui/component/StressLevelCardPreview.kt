package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressLevelHistoryCardOnePreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceOnePreview
    )
}

@Preview
@Composable
fun StressLevelHistoryCardTwoPreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceTwoPreview
    )
}

@Preview
@Composable
fun StressLevelHistoryCardThreePreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceThreePreview
    )
}

@Preview
@Composable
fun StressLevelHistoryCardFourPreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceFourPreview
    )
}

@Preview
@Composable
fun StressLevelHistoryCardFivePreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceFivePreview
    )
}

@Preview
@Composable
fun StressLevelHistoryCardWithStressorsPreview() {
    StressLevelHistoryCard(
        record = StressLevelRecordResource.stressLevelRecordResourceWithStressorsPreview
    )
}
