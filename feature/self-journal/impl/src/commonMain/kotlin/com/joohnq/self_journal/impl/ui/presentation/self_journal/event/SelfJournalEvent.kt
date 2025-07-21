package com.joohnq.self_journal.impl.ui.presentation.self_journal.event

import kotlinx.datetime.LocalDate

sealed interface SelfJournalEvent {
    data object OnGoBack : SelfJournalEvent
    data object OnNavigateToAddSelfJournal : SelfJournalEvent
    data object OnNavigateToSelfHistory : SelfJournalEvent
    data class OnClick(val localDate: LocalDate) : SelfJournalEvent
    data class OnEditSelfJournal(val id: Int) : SelfJournalEvent
}
