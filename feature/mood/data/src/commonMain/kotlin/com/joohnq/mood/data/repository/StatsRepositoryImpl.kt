package com.joohnq.mood.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.mood.domain.data_source.StatsDataSource
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import com.joohnq.shared.domain.DatetimeProvider

class StatsRepositoryImpl(
    private val dataSource: StatsDataSource,
) : StatsRepository {
    override suspend fun getStats(): List<StatsRecord> = dataSource.getStats()

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        executeTryCatchPrinting {
            dataSource.addStats(statsRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
        }

    override suspend fun deleteStat(id: Int): Boolean =
        executeTryCatchPrinting {
            dataSource.deleteStat(id)
        }
}