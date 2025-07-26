package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.mapper.toPair
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressInsightCardWorkPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Work
    )
}

@Preview
@Composable
fun StressInsightCardKidsPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Kids
    )
}

@Preview
@Composable
fun StressInsightCardInPeacePreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.InPeace
    )
}

@Preview
@Composable
fun StressInsightCardFinancesPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Finances
    )
}

@Preview
@Composable
fun StressInsightCardLifePreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Life
    )
}

@Preview
@Composable
fun StressInsightCardLonelinessPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Loneliness
    )
}

@Preview
@Composable
fun StressInsightCardOtherPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Other
    )
}

@Preview
@Composable
fun StressInsightCardRelationshipPreview() {
    StressInsightCard(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair(),
        mostActive = StressorResource.Relationship
    )
}
