package com.joohnq.core.database.converters

import kotlinx.datetime.LocalDate

object LocalDateTimeConverter {
    fun toLocalDate(dateString: String): LocalDate =
        LocalDate.parse(dateString)
}