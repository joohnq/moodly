package com.joohnq.home.impl.ui.presentation.dashboard

import com.joohnq.api.entity.User
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.toResource
import com.joohnq.freud_score.impl.ui.resource.FreudScoreResource
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.calculateStatsFreudScore
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.navigation.Destination
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getSelfJournalsInYear
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface DashboardContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object ToggleCentralExpanded : Intent
    }

    data class State(
        val user: User? = null,
        val moodItems: List<MoodRecordResource> = listOf(),
        val selfJournalItems: List<SelfJournalRecordResource> = listOf(),
        val sleepQualityItems: List<SleepQualityRecordResource> = listOf(),
        val stressLevelItems: List<StressLevelRecordResource> = listOf(),
        val gratefulnessToday: Gratefulness? = null,
        val isCentralExpanded: Boolean = false,
        val isLoading: Boolean = false,
        val isError: String? = null,
    ) {
        val freudScore: FreudScoreResource?
            get() = moodItems.calculateStatsFreudScore().toResource()

        val selfJournalItemsInYear: String
            get() = selfJournalItems.getSelfJournalsInYear()
    }

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

        data object NavigateToSelfJournalOverview : Event

        data object NavigateToSleepQualityOverview : Event

        data object NavigateToAddStressLevel : Event

        data object NavigateToStressLevelOverview : Event

        data object NavigateToAddSleepQuality : Event

        data object NavigateToAddGratefulness : Event

        data object NavigateToGratefulnessOverview : Event

        data class NavigateTo(
            val destination: Destination,
        ) : Event
    }
}
