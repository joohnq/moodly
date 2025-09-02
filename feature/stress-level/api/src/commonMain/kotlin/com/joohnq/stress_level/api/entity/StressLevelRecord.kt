package com.joohnq.stress_level.api.entity

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class StressLevelRecord(
    val id: Long = -1,
    val level: StressLevel = StressLevel.Three,
    val stressors: List<Stressor> = emptyList(),
    val createdAt: LocalDateTime = getNow(),
)
