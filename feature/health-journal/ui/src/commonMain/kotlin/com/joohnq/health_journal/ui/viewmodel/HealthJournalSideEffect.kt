package com.joohnq.health_journal.ui.viewmodel

sealed interface HealthJournalSideEffect {
    data object HealthJournalAdded : HealthJournalSideEffect
    data object HealthJournalEdited : HealthJournalSideEffect
    data object HealthJournalDeleted : HealthJournalSideEffect
    data class ShowError(val error: Throwable) : HealthJournalSideEffect
} 