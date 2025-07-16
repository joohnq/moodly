package com.joohnq.sleep_quality.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

data class SleepQualityState(
    val records: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
)