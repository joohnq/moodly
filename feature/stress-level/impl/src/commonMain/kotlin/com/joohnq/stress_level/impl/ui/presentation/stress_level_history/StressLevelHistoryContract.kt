package com.joohnq.stress_level.impl.ui.presentation.stress_level_history

sealed interface StressLevelHistoryContract {
    sealed interface Event {
        data object OnGoBack : Event

        data object OnAddStressLevel : Event

        data object OnNavigateToStressLevel : Event
    }
}
