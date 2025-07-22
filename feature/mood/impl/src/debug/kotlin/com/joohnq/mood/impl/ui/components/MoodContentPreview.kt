package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.moodRecordsResourcesListPreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.viewmodel.MoodIntent
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodContentDepressedPreview() {
    MoodContent(
        record = depressedMoodRecordResourcePreview,
        records = moodRecordsResourcesListPreview
    )
}

@Preview
@Composable
fun MoodContentSadPreview() {
    MoodContent(
        record = sadMoodRecordResourcePreview,
        records = moodRecordsResourcesListPreview
    )
}

@Preview
@Composable
fun MoodContentNeutralPreview() {
    MoodContent(
        record = neutralMoodRecordResourcePreview,
        records = moodRecordsResourcesListPreview
    )
}

@Preview
@Composable
fun MoodContentHappyPreview() {
    MoodContent(
        record = happyMoodRecordResourcePreview,
        records = moodRecordsResourcesListPreview
    )
}

@Preview
@Composable
fun MoodContentOverjoyedPreview() {
    MoodContent(
        record = overjoyedMoodRecordResourcePreview,
        records = moodRecordsResourcesListPreview
    )
}