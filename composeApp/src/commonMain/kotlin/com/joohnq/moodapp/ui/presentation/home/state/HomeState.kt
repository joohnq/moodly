package com.joohnq.moodapp.ui.presentation.home.state

import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.ui.presentation.home.event.HomeEvent
import com.joohnq.moodapp.ui.state.UiState

data class HomeState(
    val today: String = "",
    val userName: UiState<User>,
    val statsRecord: UiState<List<StatsRecord>>,
    val sleepQuality: UiState<List<SleepQualityRecord>>,
    val stressLevel: UiState<List<StressLevelRecord>>,
    val freudScore: FreudScore,
    val healthJournal: UiState<List<HealthJournalRecord>>,
    val onEvent: (HomeEvent) -> Unit = {},
)