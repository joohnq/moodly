package com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel

import com.joohnq.domain.getNow
import kotlinx.datetime.LocalDate

sealed interface SelfJournalHistoryContract {
    sealed interface Intent : SelfJournalHistoryContract {
        data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : Intent
        data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : Intent
        data class UpdateCurrentDeleteId(val id: Int) : Intent
        data class ResetState(val id: Int) : Intent
    }

    data class State(
        val selectedDateTime: LocalDate = getNow().date,
        val openDeleteDialog: Boolean = false,
        val currentDeleteId: Int = -1,
    ) : SelfJournalHistoryContract

    sealed interface Event: SelfJournalHistoryContract {
        data object GoBack : Event
        data class SelectJournalHistory(val id: Int) : Event
        data object Delete : Event
    }
}