package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.model.entities.Mood
import com.joohnq.moodapp.model.entities.SleepQuality
import com.joohnq.moodapp.model.entities.StressLevel
import com.joohnq.moodapp.view.entities.fromValue
import com.joohnq.moodapp.view.entities.toLocalDateValue
import kotlinx.datetime.LocalDate

class StatsRecordConverter {
    @TypeConverter
    fun fromMood(value: Mood): String = Mood.fromValue(value)

    @TypeConverter
    fun toMood(value: String): Mood = Mood.toValue(value)

    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): String = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: String): SleepQuality = SleepQuality.toValue(value)

    @TypeConverter
    fun fromStressLevel(value: StressLevel): String = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: String): StressLevel = StressLevel.toValue(value)

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String = value.fromValue()

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = value.toLocalDateValue()
}