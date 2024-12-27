package com.joohnq.home.ui.presentation.home.state

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.state.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.domain.entity.StressLevelRecord

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