package com.joohnq.health_journal.ui.presentation.journaling.event

sealed interface JournalingEvent {
    data class OnNavigateToEditJournalingScreen(val id: Int) : JournalingEvent
    data object OnNavigateToAllJournals : JournalingEvent
}
