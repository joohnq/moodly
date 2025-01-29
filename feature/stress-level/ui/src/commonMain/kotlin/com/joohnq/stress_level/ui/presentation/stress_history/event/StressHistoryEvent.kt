package com.joohnq.stress_level.ui.presentation.stress_history.event

sealed interface StressHistoryEvent {
    data object OnGoBack : StressHistoryEvent
    data class OnNavigateToStressLevel(val id: Int) : StressHistoryEvent
}