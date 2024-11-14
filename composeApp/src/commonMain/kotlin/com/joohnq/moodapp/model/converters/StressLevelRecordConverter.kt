package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.Stressors

class StressLevelRecordConverter {
    @TypeConverter
    fun fromStressLevel(value: StressLevel): Int = StressLevel.fromValue(value)

    @TypeConverter
    fun toStressLevel(value: Int): StressLevel = StressLevel.toValue(value)

    @TypeConverter
    fun fromStressor(value: Stressors): String = Stressors.fromValue(value)

    @TypeConverter
    fun toStressors(value: String): Stressors = Stressors.toValue(value)
}