package com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel

import kotlinx.datetime.LocalDate

sealed interface SelfJournalHistoryIntent {
    data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : SelfJournalHistoryIntent
    data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : SelfJournalHistoryIntent
    data class UpdateCurrentDeleteId(val id: Int) : SelfJournalHistoryIntent
    data class ResetState(val id: Int) : SelfJournalHistoryIntent
}