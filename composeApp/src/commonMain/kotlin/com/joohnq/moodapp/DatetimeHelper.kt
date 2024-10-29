@file:OptIn(FormatStringsInDatetimeFormats::class)

package com.joohnq.moodapp

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object DatetimeHelper {
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

    fun getCurrentMonthDaysCount(
        date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    ): Int {
        val firstDayOfNextMonth = date.plus(1, DateTimeUnit.MONTH)
        val lastDayOfCurrentMonth = firstDayOfNextMonth.minus(1, DateTimeUnit.DAY)
        return firstDayOfNextMonth.dayOfMonth
    }

}