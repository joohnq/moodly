package com.joohnq.moodapp.ui.presentation.all_journals.event

import kotlinx.datetime.LocalDate

sealed class AllJournalEvent {
    data object OnGoBack : AllJournalEvent()
    data class OnSelectJournal(val id: Int) : AllJournalEvent()
    data class OnSelectDate(val localDate: LocalDate) : AllJournalEvent()
    data class UpdateEditingOpenDeleteDialog(val value: Boolean) : AllJournalEvent()
}