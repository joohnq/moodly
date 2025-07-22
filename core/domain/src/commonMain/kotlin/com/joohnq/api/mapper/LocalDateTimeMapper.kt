package com.joohnq.api.mapper

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.until

fun LocalDateTime.toMonthNameString(): String =
    format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_FULL)
    })

fun LocalDateTime.toMonthAbbreviatedAndDayString(): String =
    format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        day(padding = Padding.ZERO)
    })

fun LocalDateTime.toMonthAbbreviatedDayAndHourFormatted(): String =
    format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        day(padding = Padding.ZERO)
        chars("\nat ")
        hour()
        char(':')
        minute()
    })

fun LocalDateTime.toMonthDays(): Int {
    val start = LocalDate(date.year, date.month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY).toInt()
}

fun LocalDateTime.toFormattedTimeString(): String =
    format(LocalDateTime.Format {
        hour()
        char(':')
        minute()
    })