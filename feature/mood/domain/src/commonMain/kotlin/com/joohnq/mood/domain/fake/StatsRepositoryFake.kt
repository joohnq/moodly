package com.joohnq.mood.domain.fake

import com.joohnq.core.test.CustomFake
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository

class StatsRepositoryFake : StatsRepository, CustomFake {
    override var shouldThrowError = false
    val items = mutableListOf(
        StatsRecord(
            id = 1,
            mood = Mood.Sad
        ),
        StatsRecord(
            id = 2,
            mood = Mood.Happy
        )
    )

    override suspend fun getStats(): Result<List<StatsRecord>> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get stats"))

        return Result.success(items)
    }

    override suspend fun addStats(statsRecord: StatsRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add stats"))

        items.add(statsRecord)

        return Result.success(true)
    }

    override suspend fun deleteStat(id: Int): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add stats"))

        val statsRecord = items.find { it.id == id }
            ?: return Result.failure(Exception("Stat with id not founded"))
        items.remove(statsRecord)

        return Result.success(true)
    }
}