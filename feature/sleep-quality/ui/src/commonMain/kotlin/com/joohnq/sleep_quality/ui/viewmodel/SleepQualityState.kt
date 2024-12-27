package com.joohnq.sleep_quality.ui.viewmodel

import com.joohnq.mood.state.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

data class SleepQualityState(
    val sleepQualityRecords: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
)