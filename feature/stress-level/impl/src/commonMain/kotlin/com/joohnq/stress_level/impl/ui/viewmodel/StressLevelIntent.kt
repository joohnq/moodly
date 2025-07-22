package com.joohnq.stress_level.impl.ui.viewmodel

import com.joohnq.stress_level.api.entity.StressLevelRecord

sealed interface StressLevelIntent {
    data object GetAll : StressLevelIntent
    data class Add(val record: StressLevelRecord) : StressLevelIntent
    data class Delete(val id: Int) : StressLevelIntent
}