package com.joohnq.sleep_quality.data.converter

import androidx.room.TypeConverter
import com.joohnq.sleep_quality.domain.entity.SleepQuality

class SleepQualityRecordConverter {
    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): Int = SleepQuality.fromValue(value)

    @TypeConverter
    fun toSleepQuality(value: Int): SleepQuality = SleepQuality.toValue(value)
}