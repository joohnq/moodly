package com.joohnq.sleep_quality.impl.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

data class SleepQualityState(
    val records: UiState<List<SleepQualityRecordResource>> = UiState.Idle,
)