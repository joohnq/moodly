package com.joohnq.stress_level.history.presentation

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import kotlinx.datetime.LocalDate

sealed interface StressLevelHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data class Delete(
            val id: Long,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val error: String,
        ) : SideEffect
    }

    data class State(
        val items: Map<LocalDate, List<StressLevelRecordResource>> = mapOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )
}
