package com.joohnq.self_journal.ui.viewmodel

sealed interface SelfJournalSideEffect {
    data object SelfJournalAdded : SelfJournalSideEffect
    data object SelfJournalEdited : SelfJournalSideEffect
    data object SelfJournalDeleted : SelfJournalSideEffect
    data class ShowError(val error: Throwable) : SelfJournalSideEffect
} 