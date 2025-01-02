package com.joohnq.stress_level.domain

import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.fromValue

object StressLevelRecordConverter {
    fun fromStressLevel(value: StressLevel): Long = value.fromValue().toLong()
    fun toStressLevel(value: Long): StressLevel = StressLevel.toValue(value.toInt())
}