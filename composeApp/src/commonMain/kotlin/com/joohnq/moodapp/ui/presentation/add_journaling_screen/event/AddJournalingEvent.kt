package com.joohnq.moodapp.ui.presentation.add_journaling_screen.event

sealed class AddJournalingEvent {
    data object OnGoBack : AddJournalingEvent()
}

