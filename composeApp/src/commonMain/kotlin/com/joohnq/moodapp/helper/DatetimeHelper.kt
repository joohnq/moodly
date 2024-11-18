package com.joohnq.moodapp.helper

import com.joohnq.moodapp.entities.StatsRecord
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

enum class DaySection(val text: String) {
    Morning("Mor"), Evening("Even"), Afternoon("Aft")
}

object DatetimeHelper {
    fun formatTime(hour: Int, minute: Int) = "${formatHour(hour)}:${formatMinute(minute)}"

    fun getLocalDateTime(): LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun formatLocalDate(): String {
        val dateTime = getLocalDateTime()
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
    fun formatLocalDate(date: LocalDate): String =
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
    fun formatLocalDate2(date: LocalDate): String = date.format(LocalDate.Format {
        dayOfMonth()
        chars("/")
        monthNumber()
    })

    fun formatLocalDateTime(date: LocalDateTime = getLocalDateTime()): String {
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

    fun monthDaysCount(
        date: LocalDateTime
    ): Int {
        val start = LocalDate(date.year, date.month, 1)
        val end = start.plus(1, DateTimeUnit.MONTH)
        return start.until(end, DateTimeUnit.DAY)
    }

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

    private fun isLeapYearUsingIfElse(year: Int): Boolean =
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

    private fun getDaysInCurrentYear(year: Int): Int =
        if (isLeapYearUsingIfElse(year)) 366 else 365

    fun getDayPerYear(
        statsRecords: List<StatsRecord?>,
        date: LocalDateTime = getLocalDateTime()
    ): String {
        val yearsDay = getDaysInCurrentYear(date.year)
        val days = statsRecords.associateBy { it?.let { formatLocalDate(date.date) } }.keys.size
        return "$days/$yearsDay"
    }

    fun formatMinute(minute: Int): String = if (minute < 10) "0$minute" else "$minute"
    fun formatHour(hour: Int): String = if (hour < 10) "0$hour" else "$hour"

    fun formatHourMinute(hour: Int, minute: Int): String =
        "${formatHour(hour)}:${formatMinute(minute)}"
}