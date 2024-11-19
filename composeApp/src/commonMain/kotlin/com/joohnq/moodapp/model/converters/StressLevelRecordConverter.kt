package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.Stressor

class StressLevelRecordConverter {
    @TypeConverter
    fun fromStressLevel(value: StressLevel): Int = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)

    @TypeConverter
    fun fromStressor(value: Stressor): String = Stressor.fromValue(value)

    @TypeConverter
    fun toStressors(value: String): Stressor = Stressor.toValue(value)
}