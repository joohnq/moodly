package com.joohnq.mood.api.mapper

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.Mood.Companion.DEPRESSED
import com.joohnq.mood.api.entity.Mood.Companion.HAPPY
import com.joohnq.mood.api.entity.Mood.Companion.NEUTRAL
import com.joohnq.mood.api.entity.Mood.Companion.OVERJOYED
import com.joohnq.mood.api.entity.Mood.Companion.SAD

object MoodMapper {
    fun Long.toMood(): Mood =
        when (this) {
            DEPRESSED.id -> Mood.Depressed
            SAD.id -> Mood.Sad
            NEUTRAL.id -> Mood.Neutral
            HAPPY.id -> Mood.Happy
            OVERJOYED.id -> Mood.Overjoyed
            else -> throw IllegalArgumentException("Unknown mood: $this")
        }
}
