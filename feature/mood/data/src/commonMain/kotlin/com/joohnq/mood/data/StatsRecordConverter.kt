package com.joohnq.mood.data

import androidx.room.TypeConverter
import com.joohnq.domain.entity.Mood

class StatsRecordConverter {
    @TypeConverter
    fun fromMood(value: Mood): Int = Mood.fromValue(value)

    @TypeConverter
    fun toMood(value: Int): Mood = Mood.toValue(value)
}

