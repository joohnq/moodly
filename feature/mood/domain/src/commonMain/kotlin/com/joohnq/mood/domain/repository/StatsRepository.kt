package com.joohnq.mood.domain.repository

import com.joohnq.mood.domain.entity.StatsRecord

interface StatsRepository {
    suspend fun getStats(): Result<List<StatsRecord>>
    suspend fun addStats(statsRecord: StatsRecord): Result<Boolean>
    suspend fun deleteStat(id: Int): Result<Boolean>
}