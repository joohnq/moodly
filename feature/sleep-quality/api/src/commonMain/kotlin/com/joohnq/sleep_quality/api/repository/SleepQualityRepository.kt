package com.joohnq.sleep_quality.api.repository

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import kotlinx.coroutines.flow.Flow

interface SleepQualityRepository {
    fun observe(): Flow<List<SleepQualityRecord>>

    suspend fun add(record: SleepQualityRecord)

    suspend fun delete(id: Long)
}
