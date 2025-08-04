package com.joohnq.self_journal.impl.ui.presentation.add

import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddSelfJournalContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event
    }

    sealed interface Intent {
        data class UpdateMood(
            val mood: MoodResource?,
        ) : Intent

        data class UpdateTitle(
            val title: String,
        ) : Intent

        data class UpdateDescription(
            val description: String,
        ) : Intent

        data class UpdateTitleError(
            val error: String?,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object OnGoBack : SideEffect

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
