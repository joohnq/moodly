package com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

sealed interface SleepQualityContract {
    sealed interface Intent : SleepQualityContract {
        data object GetAll : Intent
        data class Add(val record: SleepQualityRecord) : Intent
        data class Delete(val id: Int) : Intent
    }

    sealed interface SideEffect : SleepQualityContract {
        data object SleepQualityAdded : SideEffect
        data object SleepQualityDeleted : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val records: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
    ) : SleepQualityContract

    sealed interface Event: SleepQualityContract {
        data object GoBack : Event
        data object NavigateToAddSleepQuality : Event
        data object NavigateToSleepHistory : Event
    }
}