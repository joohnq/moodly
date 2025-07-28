package com.joohnq.mood.api.converter

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.mapper.MoodMapper.toInt
import com.joohnq.mood.api.mapper.MoodMapper.toMood

object MoodRecordConverter {
    fun fromMood(value: Mood?): Long = value.toInt().toLong()

    fun toMood(value: Long): Mood = value.toInt().toMood()
}
