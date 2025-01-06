package com.joohnq.mood.ui.mapper

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.Mood.Companion.DEPRESSED
import com.joohnq.mood.domain.entity.Mood.Companion.HAPPY
import com.joohnq.mood.domain.entity.Mood.Companion.NEUTRAL
import com.joohnq.mood.domain.entity.Mood.Companion.OVERJOYED
import com.joohnq.mood.domain.entity.Mood.Companion.SAD
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.domain.entity.SleepQuality

fun Int.toMoodResource(): MoodResource = when (this) {
    DEPRESSED.id -> MoodResource.Depressed
    SAD.id -> MoodResource.Sad
    NEUTRAL.id -> MoodResource.Neutral
    HAPPY.id -> MoodResource.Happy
    OVERJOYED.id -> MoodResource.Overjoyed
    else -> throw IllegalArgumentException("Unknown mood: $this")
}

fun MoodResource.toInt(): Int = this.id

fun getAllMoodResource(): List<MoodResource> = listOf(
    MoodResource.Depressed,
    MoodResource.Sad,
    MoodResource.Neutral,
    MoodResource.Happy,
    MoodResource.Overjoyed
)

fun Mood.toResource(): MoodResource =
    when (this) {
        is Mood.Depressed -> MoodResource.Depressed
        is Mood.Sad -> MoodResource.Sad
        is Mood.Neutral -> MoodResource.Neutral
        is Mood.Happy -> MoodResource.Happy
        is Mood.Overjoyed -> MoodResource.Overjoyed
    }

fun List<Mood>.toResource(): List<MoodResource> = map { it.toResource() }

fun MoodResource.toDomain(): Mood =
    when (this) {
        MoodResource.Depressed -> Mood.Depressed
        MoodResource.Sad -> Mood.Sad
        MoodResource.Neutral -> Mood.Neutral
        MoodResource.Happy -> Mood.Happy
        MoodResource.Overjoyed -> Mood.Overjoyed
    }

fun MoodResource.toSleepQuality(): SleepQuality =
    when (this) {
        MoodResource.Depressed -> SleepQuality.Worst
        MoodResource.Sad -> SleepQuality.Poor
        MoodResource.Neutral -> SleepQuality.Fair
        MoodResource.Happy -> SleepQuality.Good
        MoodResource.Overjoyed -> SleepQuality.Excellent
    }