package com.joohnq.add.presentation

import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddMoodContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class UpdateAddingMoodRecordMood(
            val mood: MoodResource,
        ) : Intent

        data class UpdateAddingMoodRecordDescription(
            val description: String,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object StatsAdded : SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val record: MoodRecordResource = MoodRecordResource(),
    )

    sealed interface Event {
        data object OnGoBack : Event

        data object OnNavigateToExpressionAnalysis : Event
    }
}
