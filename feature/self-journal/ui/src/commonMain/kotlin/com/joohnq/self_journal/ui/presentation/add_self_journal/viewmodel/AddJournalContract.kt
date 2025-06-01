package com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.stress_level.ui.resource.StressorResource

sealed interface AddJournalContract {
    sealed interface Intent: AddJournalContract {
        data class UpdateMood(val mood: MoodResource?) : Intent
        data class UpdateTitle(val title: String) : Intent
        data class UpdateDescription(val description: String) : Intent
        data class UpdateTitleError(val error: String?) : Intent
        data object ResetState : Intent
    }

    data class State(
        val mood: MoodResource? = null,
        val title: String = "",
        val titleError: String? = null,
        val description: String = "",
        val selectedStressStressors: List<StressorResource> = mutableListOf(),
        val sliderValue: Float = 0f,
    ): AddJournalContract

    sealed interface Event: AddJournalContract {
        data object GoBack : Event
        data object Add : Event
    }
}