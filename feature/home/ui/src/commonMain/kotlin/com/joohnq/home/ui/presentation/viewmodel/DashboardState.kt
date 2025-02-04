package com.joohnq.home.ui.presentation.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.domain.entity.StressLevelRecord

data class DashboardState(
    val user: UiState<User> = UiState.Idle,
    val statsRecords: UiState<List<StatsRecord>> = UiState.Idle,
    val freudScore: FreudScoreResource? = null,
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
    val sleepQualityRecords: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val stressLevelRecords: UiState<List<StressLevelRecord>> = UiState.Idle,
)