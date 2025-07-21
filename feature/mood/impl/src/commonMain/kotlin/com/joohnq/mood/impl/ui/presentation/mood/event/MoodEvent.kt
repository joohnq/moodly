package com.joohnq.mood.impl.ui.presentation.mood.event

sealed interface MoodEvent {
    data object OnGoBack : MoodEvent
    data object OnAddMood : MoodEvent
    data object OnNavigateToMoodHistory : MoodEvent
}
