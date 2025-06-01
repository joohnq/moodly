package com.joohnq.self_journal.ui.presentation.self_journal.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import kotlinx.datetime.LocalDate

sealed interface SelfJournalContract {
    sealed interface Intent : SelfJournalContract {
        data object GetAll : Intent
        data class Add(val record: SelfJournalRecord) : Intent
        data class Update(val record: SelfJournalRecord) : Intent
        data class Delete(val id: Int) : Intent
    }

    sealed interface SideEffect : SelfJournalContract {
        data object SelfJournalAdded : SideEffect
        data object SelfJournalUpdated : SideEffect
        data object SelfJournalDeleted : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
    ) : SelfJournalContract

    sealed interface Event : SelfJournalContract {
        data object GoBack : Event
        data object NavigateToAddSelfJournal : Event
        data object OnNavigateToSelfHistory : Event
        data class Click(val localDate: LocalDate) : Event
        data class EditSelfJournal(val id: Int) : Event
    }
}