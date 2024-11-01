@file:OptIn(FormatStringsInDatetimeFormats::class)

package com.joohnq.moodapp.helper

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until

object DatetimeHelper {
    fun getLocalDate(): LocalDate {
        val currentMoment = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun getDateTime(): String {
        val currentMoment = Clock.System.now()
        val dateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        // Mon, 28 Oct 20224
        return dateTime.date.format(LocalDate.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            chars(", ")
            dayOfMonth()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            char(' ')
            year()
        })
    }

    fun monthDaysCount(
        date: LocalDate
    ): Int {
        val start = LocalDate(date.year, date.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)
        return start.until(end, DateTimeUnit.DAY)
    }

    fun dayOfMonth(date: LocalDate): String = date.dayOfMonth.toString()

    fun monthNameAbbrev(date: LocalDate): String = date.format(LocalDate.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    })

    fun weekDayByMonth(date: LocalDate): Int = LocalDate(date.year, date.month, 1).dayOfWeek.ordinal
}