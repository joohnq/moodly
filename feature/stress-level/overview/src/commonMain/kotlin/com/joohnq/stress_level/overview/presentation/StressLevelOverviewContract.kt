package com.joohnq.stress_level.overview.presentation

import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.getStressors
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.getTodayStressLevelRecord
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toPair
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface StressLevelOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack :
            Event

        data object NavigateToStressLevelHistory : Event

        data object NavigateToAddStressLevel : Event
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
        val isLoading: Boolean = false,
        val isError: String? = null,
    ) {
        val stressorsTriggers: List<StressorResource>
            get() = items.getStressors()

        val stressorsInsight: List<Pair<StressorResource, Int>>
            get() = items.toPair()

        val historyItems: List<StressLevelRecordResource>
            get() = items.take(7)

        val todayStressLevel: StressLevelRecordResource?
            get() = items.getTodayStressLevelRecord()
    }
}
