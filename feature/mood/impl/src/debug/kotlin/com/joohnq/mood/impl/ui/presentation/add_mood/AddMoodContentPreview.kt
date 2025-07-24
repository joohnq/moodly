package com.joohnq.mood.impl.ui.presentation.add_mood

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodState
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddMoodContentDepressedPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = MoodRecordResource.moodRecordResourceDepressedPreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentSadPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = MoodRecordResource.moodRecordResourceSadPreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentNeutralPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = MoodRecordResource.moodRecordResourceNeutralPreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentHappyPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = MoodRecordResource.moodRecordResourceHappyPreview
        ),
    )
}

@Preview
@Composable
fun AddMoodContentOverjoyedPreview() {
    AddMoodContent(
        state = AddMoodState(
            record = MoodRecordResource.moodRecordResourceOverjoyedPreview
        ),
    )
}