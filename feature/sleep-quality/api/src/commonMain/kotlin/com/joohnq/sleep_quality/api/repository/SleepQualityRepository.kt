package com.joohnq.sleep_quality.api.repository

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord

interface SleepQualityRepository {
    suspend fun getSleepQualities(): Result<List<SleepQualityRecord>>
    suspend fun addSleepQuality(record: SleepQualityRecord): Result<Boolean>
    suspend fun deleteSleepQuality(id: Int): Result<Boolean>
}