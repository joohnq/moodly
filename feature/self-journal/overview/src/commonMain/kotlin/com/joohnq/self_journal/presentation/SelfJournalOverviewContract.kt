package com.joohnq.self_journal.presentation

import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

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
        val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
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