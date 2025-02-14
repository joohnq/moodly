package com.joohnq.sleep_quality.ui.viewmodel

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

sealed interface SleepQualityIntent {
    data object GetAll : SleepQualityIntent
    data class Add(val record: SleepQualityRecord) : SleepQualityIntent
    data class Delete(val id: Int) : SleepQualityIntent
}
