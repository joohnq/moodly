package com.joohnq.self_journal.impl.ui.mapper

import com.joohnq.domain.getNow
import com.joohnq.domain.mapper.getTotalDays
import com.joohnq.domain.mapper.toMonthDays
import com.joohnq.mood.domain.mapper.toAverage
import com.joohnq.mood.impl.ui.mapper.toDomain
import com.joohnq.mood.impl.ui.mapper.toResource
import com.joohnq.mood.impl.ui.resource.MoodAverageResource
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

fun SelfJournalRecordResource.toDomain(): SelfJournalRecord =
    SelfJournalRecord(
        id = id,
        mood = mood.toDomain(),
        title = title,
        description = description,
        createdAt = createdAt,
    )


fun List<SelfJournalRecordResource>.getTodaySelfJournalRecord(): SelfJournalRecordResource? =
    find { it.createdAt.date == getNow().date }

fun Map<LocalDate, List<SelfJournalRecordResource>?>.getItemsByDate(date: LocalDate): List<SelfJournalRecordResource>? {
    val key = keys.find { it == date } ?: keys.last()
    return this[key]
}

fun List<SelfJournalRecordResource>.calculateSelfJournalsAverage(): MoodAverageResource {
    if (isEmpty()) return MoodAverageResource.Skipped
    val score = sumOf { it.mood.healthLevel } / size
    return score.toAverage().toResource()
}

fun List<SelfJournalRecordResource?>.getSelfJournalsInYear(date: LocalDateTime = getNow()): String {
    val days =
        filter { it?.createdAt?.year == date.year }
            .associateBy { it?.createdAt?.date }.keys.size
    return "$days/${date.year.getTotalDays()}"
}

fun List<SelfJournalRecordResource>.organizeByDateSelfJournal(
    date: LocalDateTime = getNow(),
): Map<LocalDate, List<SelfJournalRecordResource>?> {
    val recordsByDay = groupBy { it.createdAt.date }

    return (1..date.toMonthDays()).associate { day ->
        val localDate = LocalDate(date.year, date.month, day)
        localDate to recordsByDay[localDate]
    }
}

fun List<SelfJournalRecordResource>.organizeFromCreationSelfJournalFreudScore(
    creationAt: LocalDate = getNow().date,
): Map<LocalDate, List<SelfJournalRecordResource>?> {
    val now = getNow().date
    val recordsByDay = groupBy { it.createdAt.date }
    val dateSequence = generateDateSequence(creationAt, now)
    return dateSequence.associateWith { date -> recordsByDay[date] }
}

private fun generateDateSequence(
    creationDate: LocalDate,
    currentDate: LocalDate,
): Sequence<LocalDate> {
    return generateSequence(creationDate) { current ->
        val nextDate = current.plus(1, DateTimeUnit.DAY)
        if (nextDate <= currentDate) nextDate else null
    }
}

fun SelfJournalRecord.toResource(): SelfJournalRecordResource = SelfJournalRecordResource(
    id = id,
    mood = mood.toResource(),
    title = title,
    description = description,
    createdAt = createdAt,
)

fun List<SelfJournalRecord>.toResource(): List<SelfJournalRecordResource> = map { it.toResource() }

fun List<SelfJournalRecordResource>.toGroupedByDate(): Map<LocalDate, List<SelfJournalRecordResource>> =
    groupBy { it.createdAt }
        .map { (key, value) ->
            key.date to value
        }.toMap()