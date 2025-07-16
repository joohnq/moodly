package com.joohnq.home.ui.presentation.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

data class DashboardState(
    val user: UiState<User> = UiState.Idle,
    val moodRecords: UiState<List<MoodRecordResource>> = UiState.Idle,
    val freudScore: FreudScoreResource? = null,
    val selfJournalRecords: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
    val sleepQualityRecords: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
    val stressLevelRecords: UiState<List<StressLevelRecordResource>> = UiState.Idle,
)