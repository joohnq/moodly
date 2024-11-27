package com.joohnq.moodapp.ui.presentation.all_journals.event

sealed class AllJournalEvent {
    data object OnGoBack : AllJournalEvent()
    data class OnSelectJournal(val id: Int) : AllJournalEvent()
    data object OnDelete : AllJournalEvent()
}