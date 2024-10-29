package com.joohnq.moodapp

import androidx.compose.foundation.layout.Arrangement
import com.joohnq.moodapp.model.entities.FreudScore
import com.joohnq.moodapp.model.entities.StatsRecord

object MoodsManager {
    fun getFreudScore(moods: List<StatsRecord?>): FreudScore {
        val score = moods.sumOf { it?.mood?.healthLevel ?: 0 } / moods.size
        return FreudScore.fromScore(score)
    }

    fun getMood(moods: List<StatsRecord?>): List<StatsRecord?> {
        val takenItems = moods.take(7)
        val nullCount = 7 - takenItems.size
        val result = List(nullCount.coerceAtLeast(0)) { null } + takenItems
        return result
    }

    fun getHealthJournal(statsRecords: List<StatsRecord?>): List<StatsRecord?> {
        val monthDaysCount = DatetimeHelper.getCurrentMonthDaysCount()

        val recordsByDay = statsRecords.filterNotNull().associateBy { it.date.dayOfMonth }

        return (1..monthDaysCount).map { day ->
            recordsByDay[day]
        }
    }
}