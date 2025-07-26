package com.joohnq.mood.api.converter

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.mapper.toInt
import com.joohnq.mood.api.mapper.toMood

object MoodRecordConverter {
    fun fromMood(value: Mood?): Long = value.toInt().toLong()
    fun toMood(value: Long): Mood = value.toInt().toMood()
}
