package com.joohnq.stress_level.domain.mapper

import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.FIVE
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.FOUR
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.ONE
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.THREE
import com.joohnq.stress_level.domain.entity.StressLevel.Companion.TWO
import com.joohnq.stress_level.domain.entity.StressLevel.Five
import com.joohnq.stress_level.domain.entity.StressLevel.Four
import com.joohnq.stress_level.domain.entity.StressLevel.One
import com.joohnq.stress_level.domain.entity.StressLevel.Three
import com.joohnq.stress_level.domain.entity.StressLevel.Two

fun Int.toStressLevel(): StressLevel = when (this) {
    ONE.id -> One
    TWO.id -> Two
    THREE.id -> Three
    FOUR.id -> Four
    FIVE.id -> Five
    else -> throw IllegalArgumentException("Unknown stress rate option: $this")
}

fun Int.toPercent(): Double = when (this) {
    1 -> 0.0
    2 -> 20.0
    3 -> 40.0
    4 -> 60.0
    else -> 100.0
}

fun StressLevel?.toInt(): Int = this?.id ?: -1
