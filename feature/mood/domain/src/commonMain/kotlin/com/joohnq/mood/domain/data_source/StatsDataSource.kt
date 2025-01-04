package com.joohnq.mood.domain.data_source

import com.joohnq.mood.domain.entity.StatsRecord

interface StatsDataSource {
    suspend fun getStats(): List<StatsRecord>
    suspend fun addStats(statsRecord: StatsRecord)
    suspend fun deleteStat(id: Int)
}