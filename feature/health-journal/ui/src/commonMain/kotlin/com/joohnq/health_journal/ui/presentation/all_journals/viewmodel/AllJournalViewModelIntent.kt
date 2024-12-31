package com.joohnq.health_journal.ui.presentation.all_journals.viewmodel

import kotlinx.datetime.LocalDate

sealed class AllJournalViewModelIntent {
    data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : AllJournalViewModelIntent()
    data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : AllJournalViewModelIntent()
    data class UpdateCurrentDeleteId(val id: Int) : AllJournalViewModelIntent()
    data class ResetState(val id: Int) : AllJournalViewModelIntent()
}