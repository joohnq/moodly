package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.helper.LocalDateHelper
import kotlinx.datetime.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): String = LocalDateHelper.fromValue(value)

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDateHelper.toValue(value)
}