package com.joohnq.health_journal.ui.presentation.health_journal.event

import kotlinx.datetime.LocalDate

sealed class HealthJournalEvent {
    data object OnGoBack : HealthJournalEvent()
    data object OnNavigateToAddHealthJournalScreen : HealthJournalEvent()
    data class OnClick(val localDate: LocalDate) : HealthJournalEvent()
}
