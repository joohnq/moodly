package com.joohnq.mood.ui.presentation.mood_history.event

sealed interface MoodHistoryEvent {
    data object OnGoBack : MoodHistoryEvent
}
