package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.event

sealed interface EditSelfJournalEvent {
    data object OnGoBack : EditSelfJournalEvent
    data object OnSave : EditSelfJournalEvent
}
