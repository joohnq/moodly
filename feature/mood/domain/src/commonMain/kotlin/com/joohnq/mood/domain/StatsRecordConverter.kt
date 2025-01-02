package com.joohnq.mood.domain

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.Mood.Companion.fromValue

object StatsRecordConverter {
    fun fromMood(value: Mood?): Long = value.fromValue().toLong()
    fun toMood(value: Long): Mood = Mood.toValue(value.toInt())
}

