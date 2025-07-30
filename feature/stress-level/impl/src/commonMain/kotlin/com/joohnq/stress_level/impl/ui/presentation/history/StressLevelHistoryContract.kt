package com.joohnq.stress_level.impl.ui.presentation.history

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface StressLevelHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event

        data object OnAddStressLevel : Event

        data object OnNavigateToStressLevel : Event
    }

    sealed interface Intent {
        data object GetAll :
            Intent
    }

    sealed interface SideEffect {
        data object Added :
            SideEffect

        data object Deleted :
            SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<StressLevelRecordResource>> = UiState.Idle,
    )
}
