package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodContentBodyDepressedPreview() {
    MoodContentBody(
        records = listOf(
            MoodRecordResource.moodRecordResourceDepressedPreview
        )
    )
}

@Preview
@Composable
fun MoodContentBodySadPreview() {
    MoodContentBody(
        records = listOf(
            MoodRecordResource.moodRecordResourceSadPreview
        )
    )
}

@Preview
@Composable
fun MoodContentBodyNeutralPreview() {
    MoodContentBody(
        records = listOf(
            MoodRecordResource.moodRecordResourceNeutralPreview
        )
    )
}

@Preview
@Composable
fun MoodContentBodyHappyPreview() {
    MoodContentBody(
        records = listOf(
            MoodRecordResource.moodRecordResourceHappyPreview
        )
    )
}

@Preview
@Composable
fun MoodContentBodyOverjoyedPreview() {
    MoodContentBody(
        records = listOf(
            MoodRecordResource.moodRecordResourceOverjoyedPreview
        )
    )
}