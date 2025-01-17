package com.joohnq.sleep_quality.ui.viewmodel

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

sealed class SleepQualityIntent {
    data object GetSleepQualityRecords : SleepQualityIntent()
    data class AddSleepQualityRecord(val sleepQualityRecord: SleepQualityRecord) :
        SleepQualityIntent()
}
