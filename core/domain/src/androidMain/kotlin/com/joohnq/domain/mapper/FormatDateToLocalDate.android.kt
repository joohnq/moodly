package com.joohnq.domain.mapper

import com.joohnq.domain.entity.FormatStyle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.format.FormatStyle as FS

actual fun LocalDate.formatDateToLocale(style: FormatStyle): String {
    val style = when (style) {
        FormatStyle.FULL -> FS.FULL
        FormatStyle.LONG -> FS.LONG
        FormatStyle.MEDIUM -> FS.MEDIUM
        FormatStyle.SHORT -> FS.SHORT
    }

    val formatter = DateTimeFormatter
        .ofLocalizedDate(style)
        .withLocale(Locale.getDefault())
    return this.toJavaLocalDate().format(formatter)
}

actual fun LocalDate.formatDateToLocale(pattern: String): String {
    val formatter = DateTimeFormatter
        .ofPattern(pattern)
    return this.toJavaLocalDate().format(formatter)
}

actual fun LocalDateTime.formatDateToLocale(style: FormatStyle): String =
    date.formatDateToLocale(style)