package com.joohnq.stress_level.data

import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.fromValue

class StressLevelRecordConverter {
    fun fromStressLevel(value: StressLevel): Int = value.fromValue()
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)
}