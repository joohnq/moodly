package com.joohnq.sleep_quality.ui.presentation.sleep_history.event

sealed interface SleepHistoryEvent {
    data class OnNavigateToSleepQuality(val id: Int) : SleepHistoryEvent
}