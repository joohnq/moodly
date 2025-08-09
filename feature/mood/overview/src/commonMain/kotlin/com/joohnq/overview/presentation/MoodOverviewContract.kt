package com.joohnq.overview.presentation

import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.getTodayMoodRecord
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.getWeekStreak
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface MoodOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

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
        val items: List<MoodRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    ) {
        val todayMood: MoodRecordResource?
            get() = items.getTodayMoodRecord()

        val streakDays: Int
            get() = items.getWeekStreak()

        val historyItems: List<MoodRecordResource>
            get() = items.take(7)
    }

    sealed interface Event {
        data object GoBack : Event

        data object NavigateToAddMood : Event

        data object NavigateToMoodHistory :
            Event
    }
}
