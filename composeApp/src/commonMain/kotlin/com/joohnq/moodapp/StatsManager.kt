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

    fun fillList(input: List<Double>): List<Double> {
        val maxSize = 8
        val size = input.size
        if (size >= maxSize) return input
        val newList = mutableListOf<Double>()
        var total = 0
        var remaining = 0
        newList.addAll(input)
        if (input.size % 2 == 0) {
            total = maxSize
            remaining = total - size
        } else {
            total = maxSize + 1
            remaining = total - size
        }
        val half = remaining.div(2)
        for (i in 1..half) {
            newList.add(0, 50.0)
            newList.add(newList.size, 50.0)
        }
        return newList
    }
}