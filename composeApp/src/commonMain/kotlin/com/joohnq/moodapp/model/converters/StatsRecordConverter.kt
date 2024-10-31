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

    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): Int = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)

    @TypeConverter
    fun fromStressLevel(value: StressLevel): Int = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String = LocalDateHelper.fromValue(value)

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDateHelper.toValue(value)
}