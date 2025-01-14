package com.joohnq.mood.domain.fake

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.core.test.CustomFake
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

class StatsRepositoryFake : StatsRepository, CustomFake {
    override var shouldThrowError = false
    val items = mutableListOf(
        StatsRecord(
            id = 1,
            mood = Mood.Sad,
            date = CoreTestConstants.FAKE_DATE
        ),
        StatsRecord(
            id = 2,
            mood = Mood.Happy,
            date = CoreTestConstants.FAKE_DATE.plus(1, DateTimeUnit.DAY)
        )
    )

    override suspend fun getStats(): Result<List<StatsRecord>> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get stats"))

        return Result.success(items)
    }

    override suspend fun getStatByDate(date: LocalDate): Result<StatsRecord?> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get stats"))

        return Result.success(items.find { it.date == date })
    }

    override suspend fun addStats(statsRecord: StatsRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add stat"))

        items.add(statsRecord)

        return Result.success(true)
    }

    override suspend fun deleteStat(id: Int): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to delete stat"))

        val statsRecord = items.find { it.id == id }
            ?: return Result.failure(Exception("Stat with id not founded"))
        items.remove(statsRecord)

        return Result.success(true)
    }
}