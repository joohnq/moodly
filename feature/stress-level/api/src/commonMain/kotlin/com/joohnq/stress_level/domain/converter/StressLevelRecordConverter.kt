package com.joohnq.stress_level.domain.converter

import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.mapper.toInt
import com.joohnq.stress_level.domain.mapper.toStressLevel

object StressLevelRecordConverter {
    fun fromStressLevel(value: StressLevel): Long = value.toInt().toLong()
    fun toStressLevel(value: Long): StressLevel = value.toInt().toStressLevel()
}