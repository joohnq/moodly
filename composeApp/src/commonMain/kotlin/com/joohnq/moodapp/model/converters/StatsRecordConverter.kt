package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.helper.LocalDateHelper
import kotlinx.datetime.LocalDate

class StatsRecordConverter {
    @TypeConverter
    fun fromMood(value: Mood): String = Mood.fromValue(value)

    @TypeConverter
    fun toMood(value: String): Mood = Mood.toValue(value)
}