package com.joohnq.stress_level.api.entity

import com.joohnq.stress_level.api.property.StressLevelProperties

data class DStressLevelProperties(
    override val id: Long,
    override val level: Int,
) : StressLevelProperties
