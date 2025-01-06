package com.joohnq.core.ui

import com.joohnq.core.ui.entity.DaySection
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

object DatetimeProvider : IDatetimeProvider {
    override fun getCurrentDateTime(): LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    // Mon, 28 Oct 20224
    override fun formatDate(date: LocalDate): String =
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
    override fun formatShortDate(date: LocalDate): String = date.format(LocalDate.Format {
        dayOfMonth()
        chars("/")
        monthNumber()
    })

    override fun formatDateTime(date: LocalDateTime): String {
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

    override fun getMonthDaysCount(
        date: LocalDateTime,
    ): Int {
        val start = LocalDate(date.year, date.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)
        return start.until(end, DateTimeUnit.DAY)
    }


    override fun getCurrentWeekDay(date: LocalDateTime): Int =
        LocalDate(date.year, date.month, 1).dayOfWeek.ordinal

    override fun formatTime(date: LocalDateTime): String = date.format(LocalDateTime.Format {
        hour()
        char(':')
        minute()
    })

    override fun getDaySection(date: LocalDateTime): String = when (date.hour) {
        in 0..11 -> DaySection.Morning.text
        in 12..17 -> DaySection.Afternoon.text
        else -> DaySection.Evening.text
    }

    override fun isLeapYear(year: Int): Boolean =
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

    override fun getDaysInYear(year: Int): Int =
        if (isLeapYear(year)) 366 else 365

    override fun formatInt(int: Int): String = if (int < 10) "0$int" else "$int"

    override fun formatTime(hour: Int, minute: Int): String =
        "${formatInt(hour)}:${formatInt(minute)}"
}