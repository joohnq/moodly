package com.joohnq.home.ui.presentation.viewmodel

import com.joohnq.domain.entity.User
import com.joohnq.freud_score.impl.resource.FreudScoreResource
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState

data class DashboardState(
    val user: UiState<User> = UiState.Idle,
    val moodRecords: UiState<List<MoodRecordResource>> = UiState.Idle,
    val freudScore: FreudScoreResource? = null,
    val selfJournalRecords: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
    val sleepQualityRecords: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
    val stressLevelRecords: UiState<List<StressLevelRecordResource>> = UiState.Idle,
)