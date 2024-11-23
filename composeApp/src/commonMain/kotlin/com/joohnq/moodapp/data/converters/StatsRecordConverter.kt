package com.joohnq.moodapp.data.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.domain.Mood

class StatsRecordConverter {
    @TypeConverter
    fun fromMood(value: Mood): String = Mood.fromValue(value)

    @TypeConverter
    fun toMood(value: String): Mood = Mood.toValue(value)
}

