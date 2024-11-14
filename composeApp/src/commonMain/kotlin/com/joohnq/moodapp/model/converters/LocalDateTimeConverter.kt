package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.char

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime): String = date.toString()

    @TypeConverter
    fun toLocalDateTime(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString, LocalDateTime.Format {
            date(LocalDate.Formats.ISO)
            char(' ')
            hour()
            char(':')
            minute()
            char(':')
            second()
        })
    }
}