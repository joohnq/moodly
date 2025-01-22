package com.joohnq.stress_level.ui.viewmodel

import com.joohnq.stress_level.domain.entity.StressLevelRecord

sealed interface StressLevelIntent {
    data object GetStressLevelRecords : StressLevelIntent
    data class AddStressLevelRecord(val stressLevelRecord: StressLevelRecord) : StressLevelIntent
}