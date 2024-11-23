package com.joohnq.moodapp.ui.presentation.health_journal.event

sealed class HealthJournalEvent {
    data object OnGoBack : HealthJournalEvent()
    data object OnNavigateToAddHealthJournalScreen : HealthJournalEvent()
}