package com.joohnq.home.impl.ui.presentation.dashboard

import com.joohnq.api.entity.User
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.navigation.Destination
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface DashboardContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent

    data class State(
        val user: User? = null,
        val moodItems: List<MoodRecordResource> = listOf(),
        val freudScore: FreudScoreResource? = null,
        val selfJournalItems: List<SelfJournalRecordResource> = listOf(),
        val sleepQualityItems: List<SleepQualityRecordResource> = listOf(),
        val stressLevelItems: List<StressLevelRecordResource> = listOf(),
        val isLoading: Boolean = false,
        val isError: String? = null,
    )

    sealed interface SideEffect {
        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    sealed interface Event {
        data object NavigateToAddSelfJournal : Event

        data object NavigateToAddMood : Event

        data object NavigateToFreudScore : Event

        data object NavigateToMoodOverview : Event

        data object NavigateToSelfJournal : Event

        data object NavigateToSleepQuality : Event

        data class NavigateToEditSelfJournal(
            val id: Int,
        ) : Event

        data object NavigateToAddStressLevel : Event

        data object NavigateToStressLevelOverview : Event

        data object NavigateToSelfJournalHistory : Event

        data object NavigateToAddSleepQuality : Event

        data class NavigateTo(
            val destination: Destination,
        ) : Event
    }
}
