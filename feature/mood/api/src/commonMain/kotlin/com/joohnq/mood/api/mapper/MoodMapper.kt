package com.joohnq.mood.api.mapper

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.Mood.*
import com.joohnq.mood.api.entity.Mood.Companion.DEPRESSED
import com.joohnq.mood.api.entity.Mood.Companion.HAPPY
import com.joohnq.mood.api.entity.Mood.Companion.NEUTRAL
import com.joohnq.mood.api.entity.Mood.Companion.OVERJOYED
import com.joohnq.mood.api.entity.Mood.Companion.SAD

fun Int.toMood(): Mood = when (this) {
    DEPRESSED.id -> Depressed
    SAD.id -> Sad
    NEUTRAL.id -> Neutral
    HAPPY.id -> Happy
    OVERJOYED.id -> Overjoyed
    else -> throw IllegalArgumentException("Unknown mood: $this")
}

fun Mood?.toInt(): Int = this?.id ?: -1
