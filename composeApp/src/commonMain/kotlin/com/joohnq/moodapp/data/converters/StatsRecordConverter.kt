package com.joohnq.moodapp.data.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.domain.Mood

class StatsRecordConverter {
    @TypeConverter
    fun fromMood(value: Mood): Int = Mood.fromValue(value)

    @TypeConverter
    fun toMood(value: Int): Mood = Mood.toValue(value)
}

