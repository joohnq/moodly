package com.joohnq.stress_level.api.converter

import com.joohnq.stress_level.api.entity.StressLevel
import com.joohnq.stress_level.api.mapper.StressLevelMapper.toInt
import com.joohnq.stress_level.api.mapper.StressLevelMapper.toStressLevel

object StressLevelRecordConverter {
    fun fromStressLevel(value: StressLevel): Long = value.toInt().toLong()

    fun toStressLevel(value: Long): StressLevel = value.toInt().toStressLevel()
}
