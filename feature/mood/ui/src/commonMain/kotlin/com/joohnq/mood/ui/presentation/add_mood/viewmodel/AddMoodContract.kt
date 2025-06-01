package com.joohnq.mood.ui.presentation.add_mood.viewmodel

import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource

sealed interface AddMoodContract {
    sealed interface Intent : AddMoodContract {
        data class UpdateMood(val mood: MoodResource) : Intent
        data class UpdateDescription(val description: String) : Intent
        data object ResetState : Intent
    }

    data class State(
        val record: MoodRecordResource = MoodRecordResource(),
    ) : AddMoodContract

    sealed interface Event : AddMoodContract {
        data object GoBack : Event
        data object NavigateToExpressionAnalysis : Event
    }
}