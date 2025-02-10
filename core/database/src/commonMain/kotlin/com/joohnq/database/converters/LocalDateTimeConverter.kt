package com.joohnq.database.converters

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {
    fun toLocalDateTime(dateString: String): LocalDateTime =
        LocalDateTime.parse(dateString.replace(" ", "T"))

    fun toLocalDate(dateString: String): LocalDate =
        LocalDate.parse(dateString)
}