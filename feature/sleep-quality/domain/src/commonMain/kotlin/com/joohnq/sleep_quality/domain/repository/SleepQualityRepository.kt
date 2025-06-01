package com.joohnq.sleep_quality.domain.repository

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

interface SleepQualityRepository {
    suspend fun getSleepQualities(): Result<List<SleepQualityRecord>>
    suspend fun addSleepQuality(record: SleepQualityRecord): Result<Boolean>
    suspend fun deleteSleepQuality(id: Int): Result<Boolean>
}