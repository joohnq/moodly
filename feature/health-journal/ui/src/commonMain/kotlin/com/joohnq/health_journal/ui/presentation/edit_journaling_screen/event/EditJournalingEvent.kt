package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event

sealed interface EditJournalingEvent {
    data object OnGoBack : EditJournalingEvent
    data object OnSave : EditJournalingEvent
}
