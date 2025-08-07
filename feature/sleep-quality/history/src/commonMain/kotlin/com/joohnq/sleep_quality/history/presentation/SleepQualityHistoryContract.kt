package com.joohnq.sleep_quality.history.presentation

import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface SleepQualityHistoryContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
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
        val items: List<SleepQualityRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )
}
