package com.joohnq.sleep_quality.overview.presentation

import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.UnidirectionalViewModel

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
            val id: Long,
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
    ) {
        val todaySleepQuality: SleepQualityRecordResource?
            get() = items.getTodaySleepQualityRecord()
    }
}
