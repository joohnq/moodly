package com.joohnq.home.ui.presentation.dashboard.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.navigation.Destination
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

sealed interface DashboardContract {
    sealed interface Event : DashboardContract {
        data class NavigateTo(val destination: Destination) : Event
        data object NavigateToAddJournaling : Event
        data object NavigateToAddMood : Event
        data object NavigateToAddSleep : Event
        data object NavigateToAddStress : Event
        data object NavigateToAddStressLevel : Event
        data class NavigateToEditJournal(val id: Int) : Event
        data object NavigateToFreudScore : Event
        data object NavigateToMood : Event
        data object NavigateToSelfJournal : Event
        data object NavigateToSelfJournalHistory : Event
        data object NavigateToSleepQuality : Event
        data object NavigateToStressLevel : Event
    }

    sealed interface Intent : DashboardContract {
        data object GetAllItems : Intent
    }

    sealed interface SideEffect : DashboardContract {
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val freudScore: FreudScoreResource? = null,
        val moodRecords: List<MoodRecordResource> = emptyList(),
        val selfJournalRecords: List<SelfJournalRecordResource> = emptyList(),
        val sleepQualityRecords: List<SleepQualityRecordResource> = emptyList(),
        val stressLevelRecords: List<StressLevelRecordResource> = emptyList(),
        val status: UiState<Unit> = UiState.Idle,
    ) : DashboardContract
}