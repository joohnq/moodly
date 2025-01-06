package com.joohnq.mood.domain.mapper

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.Mood.Companion.DEPRESSED
import com.joohnq.mood.domain.entity.Mood.Companion.HAPPY
import com.joohnq.mood.domain.entity.Mood.Companion.NEUTRAL
import com.joohnq.mood.domain.entity.Mood.Companion.OVERJOYED
import com.joohnq.mood.domain.entity.Mood.Companion.SAD
import com.joohnq.mood.domain.entity.Mood.Depressed
import com.joohnq.mood.domain.entity.Mood.Happy
import com.joohnq.mood.domain.entity.Mood.Neutral
import com.joohnq.mood.domain.entity.Mood.Overjoyed
import com.joohnq.mood.domain.entity.Mood.Sad

fun Int.toMood(): Mood = when (this) {
    DEPRESSED.id -> Depressed
    SAD.id -> Sad
    NEUTRAL.id -> Neutral
    HAPPY.id -> Happy
    OVERJOYED.id -> Overjoyed
    else -> throw IllegalArgumentException("Unknown mood: $this")
}

fun Mood?.toInt(): Int = this?.id ?: -1

fun getAllMood(): List<Mood> = listOf(
    Depressed,
    Sad,
    Neutral,
    Happy,
    Overjoyed
)