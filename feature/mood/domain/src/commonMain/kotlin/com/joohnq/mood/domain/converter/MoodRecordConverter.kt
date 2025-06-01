package com.joohnq.mood.domain.converter

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.mapper.toInt
import com.joohnq.mood.domain.mapper.toMood

object MoodRecordConverter {
    fun fromMood(value: Mood?): Long = value.toInt().toLong()
    fun toMood(value: Long): Mood = value.toInt().toMood()
}

