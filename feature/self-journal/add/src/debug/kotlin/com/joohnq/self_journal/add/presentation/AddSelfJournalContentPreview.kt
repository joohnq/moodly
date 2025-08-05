package com.joohnq.self_journal.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.mood.impl.ui.parameter.MoodResourceParameterProvider
import com.joohnq.self_journal.presentation.AddSelfJournalContent
import com.joohnq.self_journal.presentation.AddSelfJournalContract
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodResourceParameterProvider::class)
    mood: MoodResource,
) {
    AddSelfJournalContent(
        state =
            AddSelfJournalContract.State(
                mood = mood,
                title = "Title",
                titleError = null,
                description = "Description",
                selectedStressStressors = StressorResource.allStressors,
                sliderValue = 0f
            )
    )
}
