package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.property.StressorProperties

sealed class Stressor(
    override val id: String,
) : StressorProperties {
    data object Work : Stressor(WORK.id)
    data object Relationship : Stressor(RELATIONSHIP.id)
    data object Kids : Stressor(KIDS.id)
    data object Life : Stressor(LIFE.id)
    data object Finances : Stressor(FINANCES.id)
    data object Loneliness : Stressor(LONELINESS.id)
    data object InPeace : Stressor(IN_PEACE.id)
    data class Other(val other: String = "") : Stressor(other)

    companion object {
        val WORK = DStressorProperties("0")
        val RELATIONSHIP = DStressorProperties("1")
        val KIDS = DStressorProperties("2")
        val LIFE = DStressorProperties("3")
        val FINANCES = DStressorProperties("4")
        val LONELINESS = DStressorProperties("5")
        val IN_PEACE = DStressorProperties("7")
    }
}

