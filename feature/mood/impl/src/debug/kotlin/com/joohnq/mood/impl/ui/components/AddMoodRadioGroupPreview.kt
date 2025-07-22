package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddMoodRadioGroupPreviewDepressed() {
    AddMoodRadioGroup(
        selectedMood = depressedMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewSad() {
    AddMoodRadioGroup(
        selectedMood = sadMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewNeutral() {
    AddMoodRadioGroup(
        selectedMood = neutralMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewHappy() {
    AddMoodRadioGroup(
        selectedMood = happyMoodRecordResourcePreview,
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewOverjoyed() {
    AddMoodRadioGroup(
        selectedMood = overjoyedMoodRecordResourcePreview,
    )
}