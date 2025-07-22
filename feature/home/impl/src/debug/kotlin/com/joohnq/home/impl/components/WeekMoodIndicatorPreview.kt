package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WeekMoodIndicatorDepressedPreview() {
    WeekMoodIndicator(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
        ),
        resource = MoodResource.Depressed,
    )
}

@Preview
@Composable
fun WeekMoodIndicatorSadPreview() {
    WeekMoodIndicator(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
        ),
        resource = MoodResource.Sad,
    )
}

@Preview
@Composable
fun WeekMoodIndicatorNeutralPreview() {
    WeekMoodIndicator(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
        ),
        resource = MoodResource.Neutral,
    )
}

@Preview
@Composable
fun WeekMoodIndicatorHappyPreview() {
    WeekMoodIndicator(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
        ),
        resource = MoodResource.Happy,
    )
}

@Preview
@Composable
fun WeekMoodIndicatorOverjoyedPreview() {
    WeekMoodIndicator(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
        ),
        resource = MoodResource.Overjoyed,
    )
}