package com.joohnq.stress_level.api.entity

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class StressLevelRecord(
    val id: Int = -1,
    val stressLevel: StressLevel = StressLevel.Three,
    val stressors: List<Stressor> = emptyList(),
    val createdAt: LocalDateTime = getNow(),
)