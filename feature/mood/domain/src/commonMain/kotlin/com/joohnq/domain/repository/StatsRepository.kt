package com.joohnq.domain.repository

import com.joohnq.domain.entity.StatsRecord

interface StatsRepository {
    suspend fun getStats(): List<StatsRecord>
    suspend fun addStats(statsRecord: StatsRecord): Boolean
    suspend fun deleteStat(id: Int): Boolean
}