package com.joohnq.self_journal.ui.presentation.self_journal_history.event

sealed interface SelfJournalHistoryEvent {
    data object OnGoBack : SelfJournalHistoryEvent
    data class OnSelectJournalHistory(val id: Int) : SelfJournalHistoryEvent
    data object OnDelete : SelfJournalHistoryEvent
}