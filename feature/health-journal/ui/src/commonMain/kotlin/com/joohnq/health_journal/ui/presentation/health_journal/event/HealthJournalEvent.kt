package com.joohnq.health_journal.ui.presentation.health_journal.event

import kotlinx.datetime.LocalDate

sealed interface HealthJournalEvent {
    data object OnGoBack : HealthJournalEvent
    data object OnNavigateToAddHealthJournal : HealthJournalEvent
    data class OnClick(val localDate: LocalDate) : HealthJournalEvent
}
