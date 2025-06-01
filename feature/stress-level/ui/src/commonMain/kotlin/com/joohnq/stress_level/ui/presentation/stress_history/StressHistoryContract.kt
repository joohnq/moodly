package com.joohnq.stress_level.ui.presentation.stress_history

sealed interface StressHistoryContract {
    sealed interface Event : StressHistoryContract {
        data object GoBack : Event
        data object AddStressLevel : Event
        data object NavigateToStressLevel : Event
    }
}