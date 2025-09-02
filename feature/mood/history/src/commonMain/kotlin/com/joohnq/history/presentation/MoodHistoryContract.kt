package com.joohnq.history.presentation

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface MoodHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data class Delete(
            val id: Long,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val items: List<MoodRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )
}
