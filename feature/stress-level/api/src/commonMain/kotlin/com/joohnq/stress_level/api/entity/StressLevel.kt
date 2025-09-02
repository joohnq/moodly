package com.joohnq.stress_level.api.entity

import com.joohnq.stress_level.api.property.StressLevelProperties

sealed class StressLevel(
    override val id: Long,
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
    }
}
