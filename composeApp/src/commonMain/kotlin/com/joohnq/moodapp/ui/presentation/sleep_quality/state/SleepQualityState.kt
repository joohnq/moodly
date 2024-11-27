package com.joohnq.moodapp.ui.presentation.sleep_quality.state

import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.ui.presentation.sleep_quality.event.SleepQualityEvent

data class SleepQualityState(
    val sleepQualityRecords: List<SleepQualityRecord>,
    val onEvent: (SleepQualityEvent) -> Unit = {}
)