package com.joohnq.database.mapper

import kotlinx.datetime.LocalDate

object LocalDateMapper {
    fun String.toLocalDate(): LocalDate = LocalDate.parse(this)
}
