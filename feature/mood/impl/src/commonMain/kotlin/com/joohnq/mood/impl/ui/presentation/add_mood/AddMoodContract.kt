package com.joohnq.mood.impl.ui.presentation.add_mood

import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddMoodContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class UpdateAddingMoodRecordMood(val mood: MoodResource) : Intent
        data class UpdateAddingMoodRecordDescription(val description: String) : Intent
        data object ResetState : Intent
    }

    sealed interface SideEffect

    data class State(
        val record: MoodRecordResource = MoodRecordResource(),
    )

    sealed interface Event {
        data object OnGoBack : Event
        data object OnNavigateToExpressionAnalysis : Event
    }
}