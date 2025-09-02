package com.joohnq.gratefulness.history.presentation

import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.ui.UnidirectionalViewModel

sealed interface GratefulnessHistoryContract {
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
        val items: List<Gratefulness> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )
}
