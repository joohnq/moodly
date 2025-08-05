package com.joohnq.overview.presentation

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface MoodOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<MoodRecordResource>> = UiState.Idle,
    )

    sealed interface Event {
        data object GoBack : Event

        data object NavigateToAddMood : Event

        data object NavigateToMoodHistory :
            Event
    }
}