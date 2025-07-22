package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history.event

sealed interface SleepQualityHistoryEvent {
    data object OnNavigateToSleepQualityQuality : SleepQualityHistoryEvent
    data object OnGoBack : SleepQualityHistoryEvent
    data object OnAddSleepQualityQuality : SleepQualityHistoryEvent
}

