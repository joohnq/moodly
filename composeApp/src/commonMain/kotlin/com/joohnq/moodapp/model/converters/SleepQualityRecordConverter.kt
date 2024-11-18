package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.SleepQuality

class SleepQualityRecordConverter {
    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): Int = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)
}