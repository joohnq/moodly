package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.helper.LocalDateHelper
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String = LocalDateHelper.fromValue(value)

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime = LocalDateHelper.toValue(value)
}