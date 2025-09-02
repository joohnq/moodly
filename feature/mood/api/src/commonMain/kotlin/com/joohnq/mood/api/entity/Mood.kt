package com.joohnq.mood.api.entity

import com.joohnq.mood.api.property.MoodProperties

sealed class Mood(
    override val id: Long,
    override val healthLevel: Int,
) : MoodProperties {
    data object Depressed : Mood(id = DEPRESSED.id, healthLevel = DEPRESSED.healthLevel)

    data object Sad : Mood(id = SAD.id, healthLevel = SAD.healthLevel)

    data object Neutral : Mood(id = NEUTRAL.id, healthLevel = NEUTRAL.healthLevel)

    data object Happy : Mood(id = HAPPY.id, healthLevel = HAPPY.healthLevel)

    data object Overjoyed : Mood(id = OVERJOYED.id, healthLevel = OVERJOYED.healthLevel)

    companion object {
        val DEPRESSED = DMoodProperties(0, 20)
        val SAD = DMoodProperties(1, 40)
        val NEUTRAL = DMoodProperties(2, 60)
        val HAPPY = DMoodProperties(3, 80)
        val OVERJOYED = DMoodProperties(4, 100)
    }
}
