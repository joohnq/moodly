package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.helper.LocalDateHelper
import kotlinx.datetime.LocalDate

class SleepQualityRecordConverter {
    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): Int = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)
}