package com.joohnq.stress_level.ui.viewmodel

import com.joohnq.stress_level.domain.entity.StressLevelRecord

sealed interface StressLevelIntent {
    data object GetAll : StressLevelIntent
    data class Add(val record: StressLevelRecord) : StressLevelIntent
    data class Delete(val id: Int) : StressLevelIntent
}