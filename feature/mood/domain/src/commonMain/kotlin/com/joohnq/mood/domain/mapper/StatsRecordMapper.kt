package com.joohnq.mood.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthDays
import com.joohnq.mood.domain.entity.MoodRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlinx.datetime.plus

fun List<MoodRecord>.getTodayStatRecord(): MoodRecord? =
    find { it.createdAt.date == getNow().date }

fun List<MoodRecord>.getWeekRecords(): List<MoodRecord> {
    val now = getNow()
    val startOfWeek = now.date.minus(now.dayOfWeek.ordinal, DateTimeUnit.DAY)
    val endOfWeek = startOfWeek.plus(6, DateTimeUnit.DAY)
    val range = startOfWeek..endOfWeek
    return this.filter { it.createdAt.date in range }
}

fun List<MoodRecord>.getStreakDays(): Int {
    val now = getNow()
    val sortedRecords = this.map { it.createdAt.date }.distinct().sortedDescending()

    if (sortedRecords.isEmpty() || sortedRecords.first() != now) return 0

    var streak = 1
    for (i in 1 until sortedRecords.size) {
        if (sortedRecords[i] != now.date.minus(streak.toLong(), DateTimeUnit.DAY)) {
            break
        }
        streak++
    }

    return streak
}

fun List<MoodRecord>.getMonthDaysRecordsString(): String {
    val now = getNow()
    val days =
        filter { it.createdAt.month == now.month }
            .associateBy { it.createdAt.date }.keys.size
    return "$days/${now.toMonthDays()}"
}