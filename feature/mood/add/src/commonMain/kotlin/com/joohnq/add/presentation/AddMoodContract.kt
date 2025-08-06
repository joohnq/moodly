package com.joohnq.add.presentation

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddMoodContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class ChangeMood(
            val mood: MoodResource,
        ) : Intent

        data class ChangeDescription(
            val description: String,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val record: MoodRecordResource = MoodRecordResource(),
    )

    sealed interface Event {
        data object GoBack : Event

        data object NavigateNext : Event
    }
}
