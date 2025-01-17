package com.joohnq.stress_level.domain.entity

import com.joohnq.core.ui.DatetimeProvider
import kotlinx.datetime.LocalDateTime

data class StressLevelRecord(
    val id: Int = -1,
    val stressLevel: StressLevel = StressLevel.Three,
    val stressors: List<Stressor> = emptyList(),
    val createdAt: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
)