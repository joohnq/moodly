package com.joohnq.moodapp.helper

import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.entities.StatsRecord
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

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
        date: LocalDateTime = DatetimeManager.getCurrentDateTime(),
        healthJournals: List<HealthJournalRecord>
    ): Map<String, List<HealthJournalRecord>?> {
        val monthDaysCount = DatetimeManager.getMonthDaysCount(date)
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            val formattedDate = DatetimeManager.formatDate(localDate)
            formattedDate to recordsByDay[localDate]
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