package com.joohnq.mood.ui.presentation.mood_history

sealed interface MoodHistoryContract {
    sealed interface Event {
        data object GoBack : Event
    }
}