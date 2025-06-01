package com.joohnq.stress_level.ui.resource

import com.joohnq.domain.getNow
import kotlinx.datetime.LocalDateTime

data class StressLevelRecordResource(
    val id: Int = -1,
    val stressLevel: StressLevelResource = StressLevelResource.One,
    val stressors: List<StressorResource> = emptyList(),
    val createdAt: LocalDateTime = getNow(),
)