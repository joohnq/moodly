package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface SleepQualityContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event

        data object OnNavigateToAddSleepQuality : Event

        data object OnNavigateToSleepHistory : Event
    }

    sealed interface Intent {
        data object GetAll : Intent

        data class Add(
            val record: SleepQualityRecord,
        ) : Intent

        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data object Added : SideEffect

        data object Deleted : SideEffect

        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val records: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
    )
}
