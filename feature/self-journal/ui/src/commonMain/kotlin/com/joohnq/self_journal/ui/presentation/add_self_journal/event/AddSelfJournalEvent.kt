package com.joohnq.self_journal.ui.presentation.add_self_journal.event

sealed interface AddSelfJournalEvent {
    data object OnGoBack : AddSelfJournalEvent
    data object OnAdd : AddSelfJournalEvent
}

