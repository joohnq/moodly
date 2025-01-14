package com.joohnq.sleep_quality.domain.repository

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import kotlinx.datetime.LocalDate

interface SleepQualityRepository {
    suspend fun getSleepQualities(): Result<List<SleepQualityRecord>>
    suspend fun getSleepQualityByDate(date: LocalDate): Result<SleepQualityRecord?>
    suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Result<Boolean>
}