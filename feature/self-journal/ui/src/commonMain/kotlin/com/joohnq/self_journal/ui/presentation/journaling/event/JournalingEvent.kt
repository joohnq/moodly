package com.joohnq.self_journal.ui.presentation.journaling.event

sealed interface JournalingEvent {
    data class OnNavigateToEditJournaling(val id: Int) : JournalingEvent
    data object OnNavigateToAllJournals : JournalingEvent
}
