package com.joohnq.overview.presentation

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel

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
        val items: List<MoodRecordResource> = listOf(),
        val todayMood: MoodRecordResource? = null,
        val isLoading: Boolean = false,
        val isError: String? = null,
    )

    sealed interface Event {
        data object GoBack : Event

        data object NavigateToAddMood : Event

        data object NavigateToMoodHistory :
            Event
    }
}
