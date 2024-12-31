package com.joohnq.mood.data.repository

import com.joohnq.domain.DatetimeProvider
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsDataSource
import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.annotation.Single

@Single(binds = [StatsRepository::class])
class StatsRepositoryImpl(
    private val statsDataSource: StatsDataSource,
) : StatsRepository {
    override suspend fun getStats(): List<StatsRecord> = statsDataSource.getStats()

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        try {
            statsDataSource.addStats(statsRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteStat(id: Int): Boolean =
        try {
            statsDataSource.deleteStat(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}