package com.joohnq.moodapp.data.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.domain.StressLevel

class StressLevelRecordConverter {
    @TypeConverter
    fun fromStressLevel(value: StressLevel): Int = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)
}