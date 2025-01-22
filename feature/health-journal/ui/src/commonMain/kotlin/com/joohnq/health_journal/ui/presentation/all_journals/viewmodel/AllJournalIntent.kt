package com.joohnq.health_journal.ui.presentation.all_journals.viewmodel

import kotlinx.datetime.LocalDate

sealed interface AllJournalIntent {
    data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : AllJournalIntent
    data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : AllJournalIntent
    data class UpdateCurrentDeleteId(val id: Int) : AllJournalIntent
    data class ResetState(val id: Int) : AllJournalIntent
}