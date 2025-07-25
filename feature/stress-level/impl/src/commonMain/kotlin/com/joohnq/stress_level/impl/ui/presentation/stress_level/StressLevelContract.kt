package com.joohnq.stress_level.impl.ui.presentation.stress_level

import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.impl.ui.presentation.add_stress_level.AddStressLevelContract
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface StressLevelContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event
        data object OnAddStressLevel : Event
    }

    sealed interface Intent {
        data object GetAll : Intent
        data class Add(val record: StressLevelRecord) : Intent
        data class Delete(val id: Int) : Intent
    }

    sealed interface SideEffect {
        data object StressLevelAdded : SideEffect
        data object StressLevelDeleted : SideEffect
        data class ShowError(val error: String) : SideEffect
    }

    data class State(
        val records: UiState<List<StressLevelRecordResource>> = UiState.Idle,
    )
}