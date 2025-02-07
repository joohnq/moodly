package com.joohnq.sleep_quality.domain.mapper

import com.joohnq.sleep_quality.domain.entity.SleepInfluences
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.*
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.ALCOHOL_CONSUMPTION
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.ANXIETY
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.CAFFEINE
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.CHILL_SLEEP_ENVIRONMENT
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.EXCESSIVE_SCREEN_TIME
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.HIGH_STRESS
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.MEDITATION
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.NATURAL_LIGHT
import com.joohnq.sleep_quality.domain.entity.SleepInfluences.Companion.PHYSICAL_ACTIVITY

fun Int.toSleepInfluences(): SleepInfluences = when (this) {
    NATURAL_LIGHT.id -> NaturalLight
    PHYSICAL_ACTIVITY.id -> PhysicalActivity
    CHILL_SLEEP_ENVIRONMENT.id -> ChillSleepEnvironment
    MEDITATION.id -> Meditation
    CAFFEINE.id -> Caffeine
    EXCESSIVE_SCREEN_TIME.id -> ExcessiveScreenTime
    HIGH_STRESS.id -> HighStress
    ANXIETY.id -> Anxiety
    ALCOHOL_CONSUMPTION.id -> AlcoholConsumption
    else -> throw IllegalArgumentException("Unknown sleep influence option: $this")
}