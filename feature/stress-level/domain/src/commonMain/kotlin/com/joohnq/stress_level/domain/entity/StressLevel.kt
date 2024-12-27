package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.StressLevelProperties

sealed class StressLevel(
    override val id: Int,
    override val level: Int,
) : StressLevelProperties {
    data object One :
        StressLevel(ONE.id, ONE.level)

    data object Two :
        StressLevel(TWO.id, TWO.level)

    data object Three :
        StressLevel(THREE.id, THREE.level)

    data object Four :
        StressLevel(FOUR.id, FOUR.level)

    data object Five :
        StressLevel(FIVE.id, FIVE.level)

    companion object {
        val ONE = DStressLevelProperties(1, 1)
        val TWO = DStressLevelProperties(2, 2)
        val THREE = DStressLevelProperties(3, 3)
        val FOUR = DStressLevelProperties(4, 4)
        val FIVE = DStressLevelProperties(5, 5)

        fun toValue(src: Int): StressLevel = when (src) {
            ONE.id -> One
            TWO.id -> Two
            THREE.id -> Three
            FOUR.id -> Four
            FIVE.id -> Five
            else -> throw IllegalArgumentException("Unknown stress rate option: $src")
        }

        fun toPercent(level: Int): Double = when (level) {
            1 -> 0.0
            2 -> 20.0
            3 -> 40.0
            4 -> 60.0
            else -> 100.0
        }

        fun StressLevel?.fromValue(): Int = this?.id ?: -1

        fun getAll(): List<StressLevel> = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )
    }
}



