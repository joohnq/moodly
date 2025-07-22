package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryCardDepressedPreview() {
    MoodHistoryCard(
        record = depressedMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardSadPreview() {
    MoodHistoryCard(
        record = sadMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardNeutralPreview() {
    MoodHistoryCard(
        record = neutralMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardHappyPreview() {
    MoodHistoryCard(
        record = happyMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardOverjoyedPreview() {
    MoodHistoryCard(
        record = overjoyedMoodRecordResourcePreview,
    )
}