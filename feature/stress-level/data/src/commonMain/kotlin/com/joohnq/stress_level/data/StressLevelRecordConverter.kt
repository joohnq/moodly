package com.joohnq.stress_level.data

import androidx.room.TypeConverter
import com.joohnq.stress_level.domain.entity.StressLevel

class StressLevelRecordConverter {
    @TypeConverter
    fun fromStressLevel(value: StressLevel): Int = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)
}