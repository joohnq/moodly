package com.joohnq.mood.data.repository

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsDataSource
import com.joohnq.mood.domain.repository.StatsRepository
import com.joohnq.shared.domain.DatetimeProvider


class StatsRepositoryImpl(
    private val dataSource: StatsDataSource,
) : StatsRepository {
    override suspend fun getStats(): List<StatsRecord> = dataSource.getStats()

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        try {
            dataSource.addStats(statsRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteStat(id: Int): Boolean =
        try {
            dataSource.deleteStat(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}