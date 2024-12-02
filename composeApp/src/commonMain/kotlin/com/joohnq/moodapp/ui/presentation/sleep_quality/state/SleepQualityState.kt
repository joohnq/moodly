package com.joohnq.moodapp.ui.presentation.sleep_quality.state

import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.moodapp.ui.state.UiState

data class SleepQualityState(
    val sleepQualityRecords: UiState<List<SleepQualityRecord>>,
    val onEvent: (SleepQualityEvent) -> Unit = {}
)