package com.joohnq.sleep_quality.ui.presentation.sleep_quality_history

sealed interface SleepQualityHistoryContract{
    sealed interface Event : SleepQualityHistoryContract {
        data object OnNavigateToSleepQualityQuality : Event
        data object OnGoBack : Event
        data object OnAddSleepQualityQuality : Event
    }
}