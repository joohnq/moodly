package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.property.StressLevelProperties

data class DStressLevelProperties(
    override val id: Int, override val level: Int,
) : StressLevelProperties