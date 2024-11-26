package com.joohnq.moodapp.ui.presentation.journaling.event

sealed class JournalingEvent {
    data class OnNavigateToEditJournalingScreen(val id: Int) : JournalingEvent()
    data object OnNavigateToAllJournals : JournalingEvent()
}
