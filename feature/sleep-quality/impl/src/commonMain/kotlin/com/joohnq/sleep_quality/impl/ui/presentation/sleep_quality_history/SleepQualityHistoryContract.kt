package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

sealed interface SleepQualityHistoryContract {
    sealed interface Event {
        data object OnGoBack : Event

        data class OnDelete(val id: Int) : Event
    }
}