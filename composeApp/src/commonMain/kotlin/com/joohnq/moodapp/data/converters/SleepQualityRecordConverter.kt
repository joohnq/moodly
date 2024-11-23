package com.joohnq.moodapp.data.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.domain.SleepQuality

class SleepQualityRecordConverter {
    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): Int = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)
}