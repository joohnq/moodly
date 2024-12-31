package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.StressLevelProperties
import kotlinx.serialization.Serializable

@Serializable
sealed class StressLevel(
    override val id: Int,
    override val level: Int,
) : StressLevelProperties {
    @Serializable
    data object One :
        StressLevel(ONE.id, ONE.level)

    @Serializable
    data object Two :
        StressLevel(TWO.id, TWO.level)

    @Serializable
    data object Three :
        StressLevel(THREE.id, THREE.level)

    @Serializable
    data object Four :
        StressLevel(FOUR.id, FOUR.level)

    @Serializable
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

        fun fromSliderValue(value: Float): StressLevel = when (value) {
            0f -> One
            25f -> Two
            50f -> Three
            75f -> Four
            else -> Five
        }

        fun getAll(): List<StressLevel> = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )
    }
}



