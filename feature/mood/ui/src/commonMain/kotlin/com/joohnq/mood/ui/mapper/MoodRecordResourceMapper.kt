package com.joohnq.mood.ui.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthDays
import com.joohnq.mood.ui.resource.MoodRecordResource
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

fun List<MoodRecordResource>.getTodayStatRecord(): MoodRecordResource? =
    find { it.createdAt.date == getNow().date }

fun List<MoodRecordResource>.getWeekRecords(): List<MoodRecordResource> {
    val now = getNow()
    val startOfWeek = now.date.minus(now.dayOfWeek.ordinal, DateTimeUnit.DAY)
    val endOfWeek = startOfWeek.plus(6, DateTimeUnit.DAY)
    val range = startOfWeek..endOfWeek
    return this.filter { it.createdAt.date in range }
}

fun List<MoodRecordResource>.getStreakDays(): Int {
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

fun List<MoodRecordResource>.getMonthDaysRecordsString(): String {
    val now = getNow()
    val days =
        filter { it.createdAt.month == now.month }
            .associateBy { it.createdAt.date }.keys.size
    return "$days/${now.toMonthDays()}"
}

fun List<MoodRecordResource>.getNextMood(record: MoodRecordResource): MoodRecordResource? {
    return filter { it.createdAt > record.createdAt }
        .minByOrNull { it.createdAt }
}

fun List<MoodRecordResource>.getPreviousMood(record: MoodRecordResource): MoodRecordResource? {
    return filter { it.createdAt < record.createdAt }
        .maxByOrNull { it.createdAt }
}

fun List<MoodRecordResource>.getStatGroupByDateUseCase(): Map<LocalDate, List<MoodRecordResource>> {
    return groupBy { it.createdAt }
        .map { (key, value) ->
            key.date to value
        }.toMap()
}
