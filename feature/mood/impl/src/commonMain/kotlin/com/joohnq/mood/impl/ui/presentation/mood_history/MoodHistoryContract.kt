package com.joohnq.mood.impl.ui.presentation.mood_history

sealed interface MoodHistoryContract {
    sealed interface Event {
        data object OnGoBack : Event
    }
}
