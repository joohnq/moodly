package com.joohnq.self_journal.ui.presentation.self_journal.event

import kotlinx.datetime.LocalDate

sealed interface SelfJournalEvent {
    data object OnGoBack : SelfJournalEvent
    data object OnNavigateToAddSelfJournal : SelfJournalEvent
    data class OnClick(val localDate: LocalDate) : SelfJournalEvent
}
