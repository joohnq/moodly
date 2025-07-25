package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDate

interface SelfJournalHistoryContract {
    sealed interface Intent {
        data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : Intent
        data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : Intent
        data class UpdateCurrentDeleteId(val id: Int) : Intent
        data class ResetState(val id: Int) : Intent
    }

    data class State(
        val selectedDateTime: LocalDate = getNow().date,
        val openDeleteDialog: Boolean = false,
        val currentDeleteId: Int = -1,
    )

    sealed interface Event {
        data object OnGoBack : Event
        data class OnSelectJournalHistory(val id: Int) : Event
        data object OnDelete : Event
    }
}