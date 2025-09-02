package com.joohnq.sleep_quality.api.mapper

import com.joohnq.sleep_quality.api.entity.SleepInfluences
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.ALCOHOL_CONSUMPTION
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.ANXIETY
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.CAFFEINE
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.CHILL_SLEEP_ENVIRONMENT
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.EXCESSIVE_SCREEN_TIME
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.HIGH_STRESS
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.MEDITATION
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.NATURAL_LIGHT
import com.joohnq.sleep_quality.api.entity.SleepInfluences.Companion.PHYSICAL_ACTIVITY

object SleepInfluencesMapper {
    fun Long.toInfluence(): SleepInfluences =
        when (this) {
            NATURAL_LIGHT.id -> SleepInfluences.NaturalLight
            PHYSICAL_ACTIVITY.id -> SleepInfluences.PhysicalActivity
            CHILL_SLEEP_ENVIRONMENT.id -> SleepInfluences.ChillSleepEnvironment
            MEDITATION.id -> SleepInfluences.Meditation
            CAFFEINE.id -> SleepInfluences.Caffeine
            EXCESSIVE_SCREEN_TIME.id -> SleepInfluences.ExcessiveScreenTime
            HIGH_STRESS.id -> SleepInfluences.HighStress
            ANXIETY.id -> SleepInfluences.Anxiety
            ALCOHOL_CONSUMPTION.id -> SleepInfluences.AlcoholConsumption
            else -> throw IllegalArgumentException("Unknown sleep influence option: $this")
        }

    fun List<SleepInfluences>.mapToIds(): List<String> = this.map { it.id.toString() }

    fun List<String>.toInfluence(): List<SleepInfluences> = this.map { it.toLong().toInfluence() }
}
