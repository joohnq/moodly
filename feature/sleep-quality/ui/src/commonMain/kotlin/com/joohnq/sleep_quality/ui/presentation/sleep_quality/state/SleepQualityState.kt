package com.joohnq.sleep_quality.ui.presentation.sleep_quality.state

import com.joohnq.core.ui.entity.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent

data class SleepQualityState(
    val sleepQualityRecords: UiState<List<SleepQualityRecord>>,
    val onEvent: (SleepQualityEvent) -> Unit = {},
)