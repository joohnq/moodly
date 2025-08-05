package com.joohnq.self_journal.presentation

import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState
import kotlinx.datetime.LocalDate

sealed interface SelfJournalOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object GetAll :
            Intent

        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data object SelfJournalAdded :
            SideEffect

        data object Updated :
            SideEffect

        data object Deleted :
            SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
    )

    sealed interface Event {
        data object OnGoBack :
            Event

        data object OnNavigateToAddSelfJournal :
            Event

        data object OnNavigateToSelfHistory :
            Event

        data class OnClick(
            val localDate: LocalDate,
        ) : Event

        data class OnDelete(
            val id: Int,
        ) : Event

        data class OnEdit(
            val id: Int,
        ) : Event
    }
}