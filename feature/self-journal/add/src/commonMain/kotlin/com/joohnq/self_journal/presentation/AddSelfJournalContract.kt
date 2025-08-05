package com.joohnq.self_journal.presentation

import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddSelfJournalContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data class ChangeMood(
            val mood: MoodResource?,
        ) : Intent

        data class ChangeTitle(
            val title: String,
        ) : Intent

        data class ChangeDescription(
            val description: String,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object GoBack : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val mood: MoodResource? = null,
        val title: String = "",
        val titleError: String? = null,
        val description: String = "",
        val selectedStressStressors: List<StressorResource> = mutableListOf(),
        val sliderValue: Float = 0f,
    )
}