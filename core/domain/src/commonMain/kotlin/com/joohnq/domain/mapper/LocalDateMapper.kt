package com.joohnq.domain.mapper

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

fun LocalDate.toFormattedDateString(): String =
    format(LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        chars(", ")
        day(padding = Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
    })

fun LocalDate.toMonthCompleteDayAndYear(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_FULL)
        chars(" ")
        day(padding = Padding.ZERO)
        chars(", ")
        year()
    })

fun LocalDate.toShortDateString(): String =
    format(LocalDate.Format {
        day(padding = Padding.ZERO)
        chars("/")
        monthNumber()
    })

fun LocalDate.toMonthAbbreviatedAndDayString(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        day(padding = Padding.ZERO)
    })

fun LocalDate.toCompleteDateString(): String = format(LocalDate.Format {
    day(padding = Padding.ZERO)
    chars("/")
    monthNumber()
    chars("/")
    year()
})

fun LocalDate.getMonthFirstLetter(): String = this.month.name.first().toString()

fun LocalDate.toAbbreviatedMonthName(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    })