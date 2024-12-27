package com.joohnq.sleep_quality.domain.repository

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

interface SleepQualityRepository {
    suspend fun getSleepQualities(): List<SleepQualityRecord>
    suspend fun addSleepQuality(sleepQualityRecord: SleepQualityRecord): Boolean
}