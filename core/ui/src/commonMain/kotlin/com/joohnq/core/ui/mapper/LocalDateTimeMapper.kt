package com.joohnq.core.ui.mapper

import com.joohnq.core.ui.entity.DaySection
import kotlinx.datetime.*
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

fun LocalDateTime.toFormattedDateTimeString(): String =
    format(LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        chars(", ")
        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
        chars(" at ")
        hour()
        char(':')
        minute()
    })

fun LocalDateTime.toMonthNameString(): String =
    format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_FULL)
    })

fun LocalDateTime.toMonthAbbreviatedAndDayString(): String =
    format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        dayOfMonth()
    })

fun LocalDateTime.getDaysInMonth(): Int {
    val start = LocalDate(date.year, date.month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY)
}

fun LocalDateTime.getCurrentWeekDayIndex(): Int =
    LocalDate(date.year, date.month, 1).dayOfWeek.ordinal

fun LocalDateTime.toFormattedTimeString(): String =
    format(LocalDateTime.Format {
        hour()
        char(':')
        minute()
    })

fun LocalDateTime.getDayPeriod(): String =
    when (hour) {
        in 0..11 -> DaySection.Morning.text
        in 12..17 -> DaySection.Afternoon.text
        else -> DaySection.Evening.text
    }