package com.joohnq.stress_level.impl.ui.presentation.stress_history.event

sealed interface StressHistoryEvent {
    data object OnGoBack : StressHistoryEvent
    data object OnAddStressLevel : StressHistoryEvent
    data object OnNavigateToStressLevel : StressHistoryEvent
}