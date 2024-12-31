package com.joohnq.core.database.converters

import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {
    fun fromLocalDateTime(date: LocalDateTime): String = date.toString()
    fun toLocalDateTime(dateString: String): LocalDateTime = LocalDateTime.parse(dateString)
}