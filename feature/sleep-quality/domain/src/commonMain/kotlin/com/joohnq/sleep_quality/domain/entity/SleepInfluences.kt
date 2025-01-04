package com.joohnq.sleep_quality.domain.entity

import com.joohnq.sleep_quality.domain.SleepInfluencesProperties

sealed class SleepInfluences(override val id: Int) : SleepInfluencesProperties {
    data object NaturalLight :
        SleepInfluences(NATURAL_LIGHT.id)

    data object PhysicalActivity :
        SleepInfluences(PHYSICAL_ACTIVITY.id)

    data object ChillSleepEnvironment :
        SleepInfluences(CHILL_SLEEP_ENVIRONMENT.id)

    data object Meditation : SleepInfluences(MEDITATION.id)
    data object Caffeine : SleepInfluences(CAFFEINE.id)
    data object ExcessiveScreenTime :
        SleepInfluences(EXCESSIVE_SCREEN_TIME.id)

    data object HighStress : SleepInfluences(HIGH_STRESS.id)
    data object Anxiety : SleepInfluences(ANXIETY.id)
    data object AlcoholConsumption :
        SleepInfluences(ALCOHOL_CONSUMPTION.id)

    companion object {
        val NATURAL_LIGHT = DSleepInfluencesProperties(0)
        val PHYSICAL_ACTIVITY = DSleepInfluencesProperties(1)
        val CHILL_SLEEP_ENVIRONMENT = DSleepInfluencesProperties(2)
        val MEDITATION = DSleepInfluencesProperties(3)
        val CAFFEINE = DSleepInfluencesProperties(4)
        val EXCESSIVE_SCREEN_TIME = DSleepInfluencesProperties(5)
        val HIGH_STRESS = DSleepInfluencesProperties(6)
        val ANXIETY = DSleepInfluencesProperties(7)
        val ALCOHOL_CONSUMPTION = DSleepInfluencesProperties(8)

        fun getAll(): List<SleepInfluences> = listOf(
            NaturalLight,
            PhysicalActivity,
            ChillSleepEnvironment,
            Meditation,
            Caffeine,
            ExcessiveScreenTime,
            HighStress,
            Anxiety,
            AlcoholConsumption
        )

        fun toValue(src: Int): SleepInfluences = when (src) {
            NATURAL_LIGHT.id -> NaturalLight
            PHYSICAL_ACTIVITY.id -> PhysicalActivity
            CHILL_SLEEP_ENVIRONMENT.id -> ChillSleepEnvironment
            MEDITATION.id -> Meditation
            CAFFEINE.id -> Caffeine
            EXCESSIVE_SCREEN_TIME.id -> ExcessiveScreenTime
            HIGH_STRESS.id -> HighStress
            ANXIETY.id -> Anxiety
            ALCOHOL_CONSUMPTION.id -> AlcoholConsumption
            else -> throw IllegalArgumentException("Unknown sleep influence option: $src")
        }

        fun SleepInfluences?.fromValue(): Int = this?.id ?: -1
    }
}

