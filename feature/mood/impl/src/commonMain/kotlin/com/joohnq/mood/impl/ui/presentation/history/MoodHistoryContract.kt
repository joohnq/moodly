package com.joohnq.mood.impl.ui.presentation.history

import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface MoodHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event
    }

    sealed interface Intent {
        data object GetAll : Intent

        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data object StatsDeleted : SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<MoodRecordResource>> = UiState.Idle,
    )
}
