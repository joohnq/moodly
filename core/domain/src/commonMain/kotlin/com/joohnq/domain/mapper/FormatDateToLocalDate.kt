package com.joohnq.domain.mapper

import com.joohnq.domain.entity.FormatStyle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

expect fun LocalDate.formatDateToLocale(style: FormatStyle): String
expect fun LocalDate.formatDateToLocale(pattern: String): String
expect fun LocalDateTime.formatDateToLocale(style: FormatStyle): String