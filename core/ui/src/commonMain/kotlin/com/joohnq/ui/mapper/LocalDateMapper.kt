package com.joohnq.ui.mapper

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

fun LocalDate.toFormattedDateString(): String =
    format(LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        chars(", ")
        dayOfMonth()
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
    })

fun LocalDate.toMonthCompleteDayAndYear(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_FULL)
        chars(" ")
        dayOfMonth()
        chars(", ")
        year()
    })

fun LocalDate.toShortDateString(): String =
    format(LocalDate.Format {
        dayOfMonth()
        chars("/")
        monthNumber()
    })

fun LocalDate.toMonthAbbreviatedAndDayString(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        chars(" ")
        dayOfMonth()
    })

fun LocalDate.toCompleteDateString(): String = format(LocalDate.Format {
    dayOfMonth()
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