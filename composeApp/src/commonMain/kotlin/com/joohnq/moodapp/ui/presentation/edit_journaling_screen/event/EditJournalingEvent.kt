package com.joohnq.moodapp.ui.presentation.edit_journaling_screen.event

sealed class EditJournalingEvent {
    data object OnGoBack : EditJournalingEvent()
    data object OnSave : EditJournalingEvent()
}
