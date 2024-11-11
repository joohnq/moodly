@file:OptIn(FormatStringsInDatetimeFormats::class)

package com.joohnq.moodapp.helper

import com.joohnq.moodapp.entities.StatsRecord
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until

enum class DaySection(val text: String) {
    Morning("Mor"), Evening("Even"), Afternoon("Aft")
}

object DatetimeHelper {
    fun getLocalDateTime(): LocalDateTime {
        val currentMoment = Clock.System.now()
        return currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
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

    // Mon, 28 Oct 20224
    fun formatDateTime(date: LocalDate): String {
        return date.format(LocalDate.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            chars(", ")
            dayOfMonth()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            char(' ')
            year()
        })
    }

    fun formatDateTime(date: LocalDateTime): String {
        return date.format(LocalDateTime.Format {
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
        date: LocalDateTime
    ): Int {
        val start = LocalDate(date.year, date.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)
        return start.until(end, DateTimeUnit.DAY)
    }

    fun dayOfMonth(date: LocalDateTime): String = date.dayOfMonth.toString()

    fun monthNameAbbrev(date: LocalDateTime): String = date.format(LocalDateTime.Format {
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    })

    fun weekDayByMonth(date: LocalDateTime): Int =
        LocalDate(date.year, date.month, 1).dayOfWeek.ordinal

    fun hourAndMinutes(date: LocalDateTime): String = date.format(LocalDateTime.Format {
        hour()
        char(':')
        minute()
    })

    fun daySection(date: LocalDateTime): String = when (date.hour) {
        in 0..11 -> DaySection.Morning.text
        in 12..17 -> DaySection.Afternoon.text
        else -> DaySection.Evening.text
    }

    private fun isLeapYearUsingIfElse(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    private fun getDaysInCurrentYear(year: Int): Int {
        return if (isLeapYearUsingIfElse(year)) 366 else 365
    }

    fun getDayPerYear(
        statsRecords: List<StatsRecord?>,
        date: LocalDateTime = getLocalDateTime()
    ): String {
        val yearsDay = getDaysInCurrentYear(date.year)
        val days = statsRecords.associateBy { it?.let { formatDateTime(date) } }.keys.size
        return "$days/$yearsDay"
    }
}