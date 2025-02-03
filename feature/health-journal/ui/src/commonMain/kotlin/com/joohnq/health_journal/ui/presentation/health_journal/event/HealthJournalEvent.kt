package com.joohnq.health_journal.ui.presentation.health_journal.event

import com.joohnq.core.ui.event.PanelEvent
import kotlinx.datetime.LocalDate

sealed interface HealthJournalEvent {
    data object OnGoBack : HealthJournalEvent
    data object OnNavigateToAddHealthJournalScreen : HealthJournalEvent
    data class OnClick(val localDate: LocalDate) : HealthJournalEvent
}

fun PanelEvent.toHealthJournalEvent(): HealthJournalEvent =
    when (this) {
        PanelEvent.OnGoBack -> HealthJournalEvent.OnGoBack
        PanelEvent.OnAdd -> HealthJournalEvent.OnNavigateToAddHealthJournalScreen
    }