package com.joohnq.sleep_quality.domain.fake

import com.joohnq.core.test.CoreTestConstants
import com.joohnq.core.test.CustomFake
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import kotlinx.datetime.LocalDate

class SleepQualityRepositoryFake : SleepQualityRepository, CustomFake {
    override var shouldThrowError: Boolean = false
    private val items = mutableListOf(
        SleepQualityRecord(
            id = 1,
            sleepQuality = SleepQuality.Fair,
            startSleeping = "00:00",
            endSleeping = "06:00",
            sleepInfluences = listOf(),
            date = CoreTestConstants.FAKE_DATE
        )
    )

    override suspend fun getSleepQualities(): Result<List<SleepQualityRecord>> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get sleep qualities"))

        return Result.success(items)
    }

    override suspend fun getSleepQualityByDate(date: LocalDate): Result<SleepQualityRecord?> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get sleep qualities"))

        return Result.success(items.find { it.date == date })
    }

    override suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add sleep quality"))

        items.add(sleepQualityRecord)

        return Result.success(true)
    }
}