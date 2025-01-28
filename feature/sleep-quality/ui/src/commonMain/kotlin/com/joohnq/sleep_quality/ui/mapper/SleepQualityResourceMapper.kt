package com.joohnq.sleep_quality.ui.mapper

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource.Excellent
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource.Fair
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource.Good
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource.Poor
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource.Worst

fun getAllSleepQualityResource(): List<SleepQualityResource> = listOf(
    Excellent,
    Good,
    Fair,
    Poor,
    Worst
)

fun SleepQuality.toResource(): SleepQualityResource = when (this) {
    SleepQuality.Excellent -> Excellent
    SleepQuality.Good -> Good
    SleepQuality.Fair -> Fair
    SleepQuality.Poor -> Poor
    SleepQuality.Worst -> Worst
}

fun SleepQualityResource.toDomain(): SleepQuality = when (this) {
    Excellent -> SleepQuality.Excellent
    Good -> SleepQuality.Good
    Fair -> SleepQuality.Fair
    Poor -> SleepQuality.Poor
    Worst -> SleepQuality.Worst
}

fun SleepQualityResource.toMoodResource(): MoodResource = when (this) {
    Excellent -> MoodResource.Overjoyed
    Good -> MoodResource.Happy
    Fair -> MoodResource.Neutral
    Poor -> MoodResource.Sad
    Worst -> MoodResource.Depressed
}

fun Float.fromSliderValueToSleepQualityResource(): SleepQualityResource = when (this) {
    0f -> Worst
    25f -> Poor
    50f -> Fair
    75f -> Good
    else -> Excellent
}