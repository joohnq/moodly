package com.joohnq.self_journal.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddJournalingContentDepressedPreview() {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = MoodResource.Depressed,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}

@Preview
@Composable
fun AddJournalingContentSadPreview() {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = MoodResource.Sad,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}

@Preview
@Composable
fun AddJournalingContentNeutralPreview() {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = MoodResource.Neutral,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}

@Preview
@Composable
fun AddJournalingContentHappyPreview() {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = MoodResource.Happy,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}

@Preview
@Composable
fun AddJournalingContentOverjoyedPreview() {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = MoodResource.Overjoyed,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}
