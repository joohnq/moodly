package com.joohnq.self_journal.presentation

import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import kotlinx.datetime.LocalDate

interface SelfJournalHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class Delete(
            val id: Long,
        ) : Intent
    }

    data class State(
        val items: Map<LocalDate, List<SelfJournalRecordResource>> = mapOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    sealed interface Event {
        data object GoBack : Event

        data class NavigateToEditSelfJournal(
            val id: Long,
        ) : Event
    }
}
