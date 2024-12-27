package com.joohnq.mood.util.helper

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

object CalculateStatsFreudScore {
    operator fun invoke(statsRecords: List<StatsRecord?>): FreudScore {
        val score = statsRecords.sumOf { it?.mood?.healthLevel ?: 0 } / statsRecords.size
        return FreudScore.fromScore(score)
    }
}

class CalculateHealthJournalFreudScoreUseCase {
    operator fun invoke(healthJournalRecords: List<HealthJournalRecord>): FreudScore? {
        if (healthJournalRecords.isEmpty()) return null
        val score = healthJournalRecords.sumOf { it.mood.healthLevel } / healthJournalRecords.size
        return FreudScore.fromScore(score)
    }
}

class OrganizeFromCreationHealthJournalFreudScoreUseCase {
    operator fun invoke(
        currentDate: LocalDateTime,
        creationDate: LocalDateTime,
        healthJournals: List<HealthJournalRecord>
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val recordsByDay = healthJournals.groupBy { it.date.date }
        val dateSequence = generateDateSequence(creationDate, currentDate)
        return dateSequence.associate { date ->
            val localDate = LocalDate(date.year, date.month, date.dayOfMonth)
            localDate to recordsByDay[localDate]
        }
    }

    private fun generateDateSequence(
        creationDate: LocalDateTime,
        currentDate: LocalDateTime
    ): Sequence<LocalDate> {
        return generateSequence(creationDate.date) { current ->
            val nextDate = current.plus(1, DateTimeUnit.DAY)
            if (nextDate <= currentDate.date) nextDate else null
        }
    }
}

class OrganizeByDateHealthJournalUseCase {
    operator fun invoke(
        currentDate: LocalDateTime,
        monthDaysCount: Int,
        healthJournals: List<HealthJournalRecord>
    ): Map<LocalDate, List<HealthJournalRecord>?> {
        val recordsByDay = healthJournals.groupBy { it.date.date }

        return (1..monthDaysCount).associate { day ->
            val localDate = LocalDate(currentDate.year, currentDate.month, day)
            localDate to recordsByDay[localDate]
        }
    }
}

class GetNextStatUseCase {
    operator fun invoke(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date > statsRecord.date }
            .minByOrNull { it.date }
}


class GetPreviousStatUseCase {
    operator fun invoke(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date < statsRecord.date }
            .maxByOrNull { it.date }
}

class GetStatGroupByDateUseCase {
    operator fun invoke(
        statsRecords: List<StatsRecord>,
        formatKey: (LocalDate) -> String
    ): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                formatKey(key) to value
            }
            .toMap()
}

class OrganizeStatRangeUseCase {
    operator fun invoke(input: List<Double>): List<Double> =
        if (input.size < 8) listOf(0.0) + input + 0.0 else input
}
