package com.joohnq.sleep_quality.api.entity

import com.joohnq.sleep_quality.api.property.SleepInfluencesProperties

sealed class SleepInfluences(
    override val id: Long,
) : SleepInfluencesProperties {
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
    }
}
