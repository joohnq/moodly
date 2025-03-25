package com.joohnq.domain.mapper

import com.joohnq.domain.entity.FormatStyle
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames

fun LocalDate.toFormattedDateString(): String =
    formatDateToLocale("EEE, dd MMM yyyy")

fun LocalDate.toMonthCompleteDayAndYear(): String =
    formatDateToLocale(FormatStyle.LONG)

fun LocalDate.toShortDateString(): String =
    format(LocalDate.Format {
        dayOfMonth()
        chars("/")
        monthNumber()
    })

fun LocalDate.toCompleteDateString(): String =
    formatDateToLocale(FormatStyle.LONG)

fun LocalDate.toAbbreviatedMonthName(): String =
    format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    })