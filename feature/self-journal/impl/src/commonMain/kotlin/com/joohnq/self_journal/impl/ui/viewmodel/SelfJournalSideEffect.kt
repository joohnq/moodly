package com.joohnq.self_journal.impl.ui.viewmodel

sealed interface SelfJournalSideEffect {
    data object SelfJournalAdded : SelfJournalSideEffect
    data object Updated : SelfJournalSideEffect
    data object SelfJournalDeleted : SelfJournalSideEffect
    data class ShowError(val error: String) : SelfJournalSideEffect
} 