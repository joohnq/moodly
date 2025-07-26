package com.joohnq.home.impl.presentation.viewmodel

import com.joohnq.api.entity.User
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.navigation.Destination
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.ui.entity.UiState

sealed interface DashboardContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data object Get : Intent
    }

    data class State(
        val user: UiState<User> = UiState.Idle,
        val moodRecords: UiState<List<MoodRecordResource>> = UiState.Idle,
        val freudScore: FreudScoreResource? = null,
        val selfJournalRecords: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
        val sleepQualityRecords: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
        val stressLevelRecords: UiState<List<StressLevelRecordResource>> = UiState.Idle
    )

    sealed interface SideEffect {
        data class ShowError(val message: String) : SideEffect
    }

    sealed interface Event {
        data object OnNavigateToAddJournaling : Event
        data object OnNavigateToAddStress : Event
        data object OnNavigateToAddMood : Event
        data object OnNavigateToFreudScore : Event
        data object OnNavigateToMood : Event
        data object OnNavigateToSelfJournal : Event
        data object OnNavigateToSleepQuality : Event
        data object OnNavigateToStressLevel : Event
        data object OnNavigateToAddStressLevel : Event
        data class OnNavigateToEditJournaling(val id: Int) : Event
        data object OnNavigateToSelfJournalHistory : Event
        data object OnNavigateToAddSleep : Event
        data class OnNavigateTo(val destination: Destination) : Event
    }
}
