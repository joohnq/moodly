package com.joohnq.self_journal.presentation

import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface SelfJournalOverviewContract {
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
        val items: List<SelfJournalRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )

    sealed interface Event {
        data object GoBack :
            Event

        data object NavigateToAddSelfJournal :
            Event

        data object NavigateToSelfJournalHistory :
            Event

        data class NavigateToEditSelfJournal(
            val id: Int,
        ) : Event
    }
}
