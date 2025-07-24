package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodRadioGroupPreviewDepressed() {
    MoodRadioGroup(
        selectedMood = MoodRecordResource.moodRecordResourceDepressedPreview,
    )
}

@Preview
@Composable
fun MoodRadioGroupPreviewSad() {
    MoodRadioGroup(
        selectedMood = MoodRecordResource.moodRecordResourceSadPreview,
    )
}

@Preview
@Composable
fun MoodRadioGroupPreviewNeutral() {
    MoodRadioGroup(
        selectedMood = MoodRecordResource.moodRecordResourceNeutralPreview,
    )
}

@Preview
@Composable
fun MoodRadioGroupPreviewHappy() {
    MoodRadioGroup(
        selectedMood = MoodRecordResource.moodRecordResourceHappyPreview,
    )
}

@Preview
@Composable
fun MoodRadioGroupPreviewOverjoyed() {
    MoodRadioGroup(
        selectedMood = MoodRecordResource.moodRecordResourceOverjoyedPreview,
    )
}