package com.joohnq.stress_level.ui.presentation.stress_level.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

sealed interface StressLevelContract {
    sealed interface Intent : StressLevelContract {
        data object GetAll : Intent
        data class Add(val record: StressLevelRecord) : Intent
        data class Delete(val id: Int) : Intent
    }

    sealed interface SideEffect : StressLevelContract {
        data object StressLevelAdded : SideEffect
        data object StressLevelDeleted : SideEffect
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val records: UiState<List<StressLevelRecordResource>> = UiState.Idle,
    ) : StressLevelContract

    sealed interface Event: StressLevelContract {
        data object GoBack : Event
        data object AddStressLevel : Event
    }
}