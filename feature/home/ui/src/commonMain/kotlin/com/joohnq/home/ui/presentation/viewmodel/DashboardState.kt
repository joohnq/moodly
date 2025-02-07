package com.joohnq.home.ui.presentation.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.domain.entity.StressLevelRecord

data class DashboardState(
    val user: UiState<User> = UiState.Idle,
    val moodRecords: UiState<List<MoodRecord>> = UiState.Idle,
    val freudScore: FreudScoreResource? = null,
    val selfJournalRecords: UiState<List<SelfJournalRecord>> = UiState.Idle,
    val sleepQualityRecords: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val stressLevelRecords: UiState<List<StressLevelRecord>> = UiState.Idle,
)