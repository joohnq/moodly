package com.joohnq.overview.presentation

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface MoodOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object GetAll : Intent

        data class Add(
            val record: MoodRecord,
        ) : Intent

        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data object StatsDeleted :
            SideEffect

        data object Added :
            SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<MoodRecordResource>> = UiState.Idle,
    )

    sealed interface Event {
        data object OnGoBack : Event

        data object OnAddMood : Event

        data object OnNavigateToMoodHistory :
            Event
    }
}
