package com.joohnq.stress_level.domain.entity

import com.joohnq.stress_level.domain.StressorProperties
import kotlinx.serialization.Serializable

@Serializable
sealed class Stressor(
    override val id: String
) : StressorProperties {
    @Serializable
    data object Work : Stressor(WORK.id)

    @Serializable
    data object Relationship : Stressor(RELATIONSHIP.id)

    @Serializable
    data object Kids : Stressor(KIDS.id)

    @Serializable
    data object Life : Stressor(LIFE.id)

    @Serializable
    data object Finances : Stressor(FINANCES.id)

    @Serializable
    data object Loneliness : Stressor(LONELINESS.id)

    @Serializable
    data object InPeace : Stressor(IN_PEACE.id)

    @Serializable
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

