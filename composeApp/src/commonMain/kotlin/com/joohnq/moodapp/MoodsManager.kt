package com.joohnq.moodapp

import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object MoodsManager {
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
            val formattedDate = DatetimeHelper.formatDateTime(localDate)
            formattedDate to recordsByDay[localDate]
        }
    }


    fun getNext(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.find { item -> item.date > statsRecord.date }

    fun getPrevious(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.find { item -> item.date < statsRecord.date }

    fun getMap(statsRecords: List<StatsRecord>): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                DatetimeHelper.formatDateTime(key) to value
            }
            .toMap()

}