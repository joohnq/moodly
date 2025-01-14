package com.joohnq.health_journal.domain.fake

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.core.test.CustomFake
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.mood.domain.entity.Mood
import kotlinx.datetime.LocalDate

class HealthJournalRepositoryFake : HealthJournalRepository, CustomFake {
    override var shouldThrowError: Boolean = false
    private val items = mutableListOf(
        HealthJournalRecord(
            id = 1,
            title = "title",
            description = "description",
            date = CoreTestConstants.FAKE_DATE,
            mood = Mood.Depressed
        ),
        HealthJournalRecord(
            id = 2,
            title = "title 2",
            description = "description 2",
            date = CoreTestConstants.FAKE_DATE,
            mood = Mood.Neutral
        )
    )

    override suspend fun getHealthJournals(): Result<List<HealthJournalRecord>> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get health journals"))

        return Result.success(items)
    }

    override suspend fun getHealthJournalByDate(date: LocalDate): Result<HealthJournalRecord?> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get health journals"))

        return Result.success(items.find { it.date == date })
    }

    override suspend fun addHealthJournal(healthJournalRecord: HealthJournalRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add health journal"))

        items.add(healthJournalRecord)
        return Result.success(true)
    }

    override suspend fun deleteHealthJournal(id: Int): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to delete health journal"))

        val item = items.find { it.id == id }
            ?: return Result.failure(Exception("Health journal with id not founded"))

        items.remove(item)
        return Result.success(true)
    }

    override suspend fun updateHealthJournal(healthJournal: HealthJournalRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update health journal"))

        val item = items.find { it.id == healthJournal.id }
            ?: return Result.failure(Exception("Health journal with id not founded"))

        val i = items.indexOf(item)
        items.remove(item)
        items.add(i, item)
        return Result.success(true)
    }
}