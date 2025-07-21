package com.joohnq.self_journal.impl.ui.presentation.add_self_journal.event

sealed interface AddSelfJournalEvent {
    data object OnGoBack : AddSelfJournalEvent
    data object OnAdd : AddSelfJournalEvent
}

