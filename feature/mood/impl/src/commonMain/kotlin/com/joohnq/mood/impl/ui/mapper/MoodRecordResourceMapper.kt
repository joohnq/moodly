package com.joohnq.mood.impl.ui.mapper

import com.joohnq.domain.getNow
import com.joohnq.domain.mapper.toMonthDays
import com.joohnq.freud_score.api.entity.FreudScore
import com.joohnq.freud_score.api.mapper.toFreudScore
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.resource.MoodRecordResource
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlinx.datetime.plus

fun List<MoodRecordResource>.getTodayMoodRecord(): MoodRecordResource? =
    find { it.createdAt.date == getNow().date }

fun List<MoodRecordResource>.getWeekRecords(): List<MoodRecordResource> {
    val now = getNow()
    val startOfWeek = now.date.minus(now.dayOfWeek.ordinal, DateTimeUnit.DAY)
    val endOfWeek = startOfWeek.plus(6, DateTimeUnit.DAY)
    val range = startOfWeek..endOfWeek
    return this.filter { it.createdAt.date in range }
}

fun List<MoodRecordResource>.getStreakDays(): Int {
    val now = getNow().date
    val sortedRecords = this.map { it.createdAt.date }.distinct().sortedDescending()

    if (sortedRecords.isEmpty() || sortedRecords.first() != now) return 0

    var streak = 1
    for (i in 1 until sortedRecords.size) {
        if (sortedRecords[i] != now.minus(streak.toLong(), DateTimeUnit.DAY)) {
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

fun List<MoodRecordResource?>.calculateStatsFreudScore(): FreudScore {
    if (isEmpty()) return FreudScore.NotAvailable
    val score = sumOf { it?.mood?.healthLevel ?: 0 } / size
    return score.toFreudScore()
}

fun MoodRecordResource.toDomain(): MoodRecord = MoodRecord(
    id = id,
    mood = mood.toDomain(),
    description = description,
    createdAt = createdAt,
)

fun List<MoodRecordResource>.toDomain(): List<MoodRecord> = map { it.toDomain() }

fun MoodRecord.toResource(): MoodRecordResource =
    MoodRecordResource(
        id = id,
        mood = mood.toResource(),
        description = description,
        createdAt = createdAt,
    )

fun List<MoodRecord>.toResource(): List<MoodRecordResource> = map { it.toResource() }


