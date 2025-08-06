package com.joohnq.sleep_quality.overview.presentation

import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface SleepQualityOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack :
            Event

        data object NavigateToAddSleepQuality :
            Event

        data object NavigateToSleepQualityHistory :
            Event
    }

    sealed interface Intent {
        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
    )
}
