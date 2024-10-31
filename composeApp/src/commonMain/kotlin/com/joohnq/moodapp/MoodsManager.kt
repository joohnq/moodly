package com.joohnq.moodapp

import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord

object MoodsManager {
    fun getFreudScore(moods: List<StatsRecord?>): FreudScore {
        val score = moods.sumOf { it?.mood?.healthLevel ?: 0 } / moods.size
        return FreudScore.fromScore(score)
    }

    fun getHealthJournal(statsRecords: List<StatsRecord?>): List<StatsRecord?> {
        val monthDaysCount = DatetimeHelper.getCurrentMonthDaysCount()

        val recordsByDay = statsRecords.filterNotNull().associateBy { it.date.dayOfMonth }

        return (1..monthDaysCount).map { day ->
            recordsByDay[day]
        }
    }
}