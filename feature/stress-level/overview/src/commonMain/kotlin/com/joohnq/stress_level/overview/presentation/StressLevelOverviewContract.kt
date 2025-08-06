package com.joohnq.stress_level.overview.presentation

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface StressLevelOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack :
            Event

        data object NavigateToAddStressLevel :
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
        val items: List<StressLevelRecordResource> = listOf(),
        val todayStressLevel: StressLevelRecordResource? = null,
        val isLoading: Boolean = false,
        val isError: String? = null,
    )
}
