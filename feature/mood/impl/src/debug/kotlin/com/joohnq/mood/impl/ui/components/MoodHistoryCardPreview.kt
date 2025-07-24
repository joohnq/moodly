package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodHistoryCardDepressedPreview() {
    MoodHistoryCard(
        record = MoodRecordResource.moodRecordResourceDepressedPreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardSadPreview() {
    MoodHistoryCard(
        record = MoodRecordResource.moodRecordResourceSadPreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardNeutralPreview() {
    MoodHistoryCard(
        record = MoodRecordResource.moodRecordResourceNeutralPreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardHappyPreview() {
    MoodHistoryCard(
        record = MoodRecordResource.moodRecordResourceHappyPreview,
    )
}

@Preview
@Composable
fun MoodHistoryCardOverjoyedPreview() {
    MoodHistoryCard(
        record = MoodRecordResource.moodRecordResourceOverjoyedPreview,
    )
}