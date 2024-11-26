package com.joohnq.moodapp.util.helper

import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.StatsRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

object StatsManager {
    fun getFreudScore(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }

    fun getHealthJournalFreudScore(healthJournalRecords: List<HealthJournalRecord>): FreudScore? {
        if (healthJournalRecords.isEmpty()) return null
        val score = healthJournalRecords.sumOf { it.mood.healthLevel } / healthJournalRecords.size
        return FreudScore.fromScore(score)
    }

    fun getHealthJournal(
        healthJournals: List<HealthJournalRecord>
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val date = DatetimeManager.getCurrentDateTime()
        val monthDaysCount = DatetimeManager.getMonthDaysCount(date)
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            localDate to recordsByDay[localDate]
        }
    }

    fun getHealthJournalBasedOnUserEntry(
        creationDate: LocalDateTime,
        healthJournals: List<HealthJournalRecord>
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val currentDate = DatetimeManager.getCurrentDateTime()
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return generateSequence(creationDate.date) { current ->
            val nextDate = current.plus(1, DateTimeUnit.DAY)
            if (nextDate <= currentDate.date) nextDate else null
        }.associate { date ->
            val localDate = LocalDate(date.year, date.month, date.dayOfMonth)
            localDate to recordsByDay[localDate]
        }
    }

    fun getNext(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date > statsRecord.date }
            .minByOrNull { it.date }

    fun getPrevious(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date < statsRecord.date }
            .maxByOrNull { it.date }

    fun getGroupByDate(statsRecords: List<StatsRecord>): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                DatetimeManager.formatDate(key) to value
            }
            .toMap()

    fun normalizeRange(input: List<Double>): List<Double> =
        if (input.size < 8) listOf(0.0) + input + 0.0 else input
}