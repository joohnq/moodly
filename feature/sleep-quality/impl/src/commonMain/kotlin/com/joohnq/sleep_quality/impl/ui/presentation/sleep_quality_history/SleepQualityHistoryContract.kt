package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

sealed interface SleepQualityHistoryContract {
    sealed interface Event {
        data object OnNavigateToSleepQualityQuality : Event
        data object OnGoBack : Event
        data object OnAddSleepQualityQuality : Event
    }
}