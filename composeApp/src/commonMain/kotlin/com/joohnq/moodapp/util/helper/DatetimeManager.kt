package com.joohnq.moodapp.util.helper

import com.joohnq.moodapp.domain.DaySection
import com.joohnq.moodapp.domain.HealthJournalRecord
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until

object DatetimeManager {
    fun getCurrentDateTime(): LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    // Mon, 28 Oct 20224
    fun formatDate(date: LocalDate = getCurrentDateTime().date): String =
        date.format(LocalDate.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            chars(", ")
            dayOfMonth()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            char(' ')
            year()
        })

    // Mon, 28 Oct 20224
    fun formatShortDate(date: LocalDate): String = date.format(LocalDate.Format {
        dayOfMonth()
        chars("/")
        monthNumber()
    })

    fun formatDateTime(date: LocalDateTime = getCurrentDateTime()): String {
        return date.format(LocalDateTime.Format {
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
    }

    fun getMonthDaysCount(
        date: LocalDateTime
    ): Int {
        val start = LocalDate(date.year, date.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)
        return start.until(end, DateTimeUnit.DAY)
    }

    fun getHealthJournalsInYear(
        healthJournals: List<HealthJournalRecord?>,
        date: LocalDateTime = getCurrentDateTime()
    ): String {
        val yearsDay = getDaysInYear(date.year)
        val days = healthJournals.associateBy { it?.let { formatDate(date.date) } }.keys.size
        return "$days/$yearsDay"
    }

    fun getCurrentWeekDay(date: LocalDateTime): Int =
        LocalDate(date.year, date.month, 1).dayOfWeek.ordinal

    fun formatTime(date: LocalDateTime): String = date.format(LocalDateTime.Format {
        hour()
        char(':')
        minute()
    })

    fun getDaySection(date: LocalDateTime): String = when (date.hour) {
        in 0..11 -> DaySection.Morning.text
        in 12..17 -> DaySection.Afternoon.text
        else -> DaySection.Evening.text
    }

    private fun isLeapYear(year: Int): Boolean =
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

    private fun getDaysInYear(year: Int): Int =
        if (isLeapYear(year)) 366 else 365

    fun formatInt(int: Int): String = if (int < 10) "0$int" else "$int"

    fun formatTime(hour: Int, minute: Int): String =
        "${formatInt(hour)}:${formatInt(minute)}"
}