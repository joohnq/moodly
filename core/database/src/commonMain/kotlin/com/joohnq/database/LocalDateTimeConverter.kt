package com.joohnq.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {
    @TypeConverter
    fun from(value: String): LocalDateTime = LocalDateTime.parse(value)

    @TypeConverter
    fun to(date: LocalDateTime): String = date.toString()
}
