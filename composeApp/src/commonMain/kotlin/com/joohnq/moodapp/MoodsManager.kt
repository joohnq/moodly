package com.joohnq.moodapp

import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import kotlinx.datetime.LocalDate

object MoodsManager {
    fun getFreudScore(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }

    fun getHealthJournal(
        date: LocalDate = DatetimeHelper.getLocalDate(),
        statsRecords: List<StatsRecord?>
    ): List<StatsRecord?> {
        val monthDaysCount = DatetimeHelper.monthDaysCount(date)

        val recordsByDay = statsRecords.filterNotNull().associateBy { it.date.dayOfMonth }

        return (1..monthDaysCount).map { day -> recordsByDay[day] }
    }

    fun getNext(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.find { item -> item.date > statsRecord.date }

    fun getPrevious(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.find { item -> item.date < statsRecord.date }
}