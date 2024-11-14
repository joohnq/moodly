package com.joohnq.moodapp

import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object StatsManager {
    fun getFreudScore(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }

    fun getHealthJournal(
        date: LocalDateTime = DatetimeHelper.getLocalDateTime(),
        statsRecords: List<StatsRecord>
    ): Map<String, List<StatsRecord>?> {
        val monthDaysCount = DatetimeHelper.monthDaysCount(date)
        val recordsByDay = statsRecords.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(date.year, date.month, day)
            val formattedDate = DatetimeHelper.formatLocalDate(localDate)
            formattedDate to recordsByDay[localDate]
        }
    }

    fun getNext(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date > statsRecord.date }
            .minByOrNull { it.date }

    fun getPrevious(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date < statsRecord.date }
            .maxByOrNull { it.date }

    fun getMap(statsRecords: List<StatsRecord>): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                DatetimeHelper.formatLocalDate(key) to value
            }
            .toMap()

    fun addHalfInStartAndEnd(input: List<Double>): List<Double> =
        if (input.size < 8) listOf(0.0) + input + 0.0 else input
}