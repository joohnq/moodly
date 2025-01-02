package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.StressorProperties
import kotlinx.serialization.Serializable

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

        fun toValue(src: String): Stressor = when (src) {
            WORK.id -> Work
            RELATIONSHIP.id -> Relationship
            KIDS.id -> Kids
            LIFE.id -> Life
            LONELINESS.id -> Loneliness
            FINANCES.id -> Finances
            IN_PEACE.id -> InPeace
            else -> Other(src)
        }

        fun Stressor?.fromValue(): String = this?.id ?: ""

        fun getAll(): List<Stressor> =
            listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())
    }
}

