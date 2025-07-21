package com.joohnq.mood.impl.ui.presentation.mood_history.event

sealed interface MoodHistoryEvent {
    data object OnGoBack : MoodHistoryEvent
}
