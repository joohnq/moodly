package com.joohnq.stress_level.api.mapper

import com.joohnq.stress_level.api.entity.StressLevel
import com.joohnq.stress_level.api.entity.StressLevel.Companion.FIVE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.FOUR
import com.joohnq.stress_level.api.entity.StressLevel.Companion.ONE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.THREE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.TWO
import com.joohnq.stress_level.api.entity.StressLevel.Five
import com.joohnq.stress_level.api.entity.StressLevel.Four
import com.joohnq.stress_level.api.entity.StressLevel.One
import com.joohnq.stress_level.api.entity.StressLevel.Three
import com.joohnq.stress_level.api.entity.StressLevel.Two

object StressLevelMapper {
    fun Long.toStressLevel(): StressLevel =
        when (this) {
            ONE.id -> One
            TWO.id -> Two
            THREE.id -> Three
            FOUR.id -> Four
            FIVE.id -> Five
            else -> throw IllegalArgumentException("Unknown stress rate option: $this")
        }
}
