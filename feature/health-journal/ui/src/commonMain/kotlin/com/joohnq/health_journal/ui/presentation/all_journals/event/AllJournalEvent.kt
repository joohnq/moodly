package com.joohnq.health_journal.ui.presentation.all_journals.event

sealed interface AllJournalEvent {
    data object OnGoBack : AllJournalEvent
    data class OnSelectJournal(val id: Int) : AllJournalEvent
    data object OnDelete : AllJournalEvent
}