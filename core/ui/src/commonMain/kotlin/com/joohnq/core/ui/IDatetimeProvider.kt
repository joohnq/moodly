package com.joohnq.core.ui

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface IDatetimeProvider {
    fun getCurrentDateTime(): LocalDateTime

    // Mon, 28 Oct 20224
    fun formatDate(date: LocalDate = getCurrentDateTime().date): String

    // Mon, 28 Oct 20224
    fun formatShortDate(date: LocalDate): String
    fun formatDateTime(date: LocalDateTime = getCurrentDateTime()): String
    fun getMonthDaysCount(
        date: LocalDateTime,
    ): Int

    fun getCurrentWeekDay(date: LocalDateTime): Int
    fun formatTime(date: LocalDateTime): String
    fun getDaySection(date: LocalDateTime): String
    fun isLeapYear(year: Int): Boolean
    fun getDaysInYear(year: Int): Int
    fun formatInt(int: Int): String
    fun formatTime(hour: Int, minute: Int): String
}