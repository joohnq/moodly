package com.joohnq.database.mapper

import kotlinx.datetime.LocalDateTime

object LocalDateTimeMapper {
    fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this.replace(" ", "T"))
}
