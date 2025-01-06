package com.joohnq.mood.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.toResult
import com.joohnq.mood.domain.data_source.StatsDataSource
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository

class StatsRepositoryImpl(
    private val dataSource: StatsDataSource,
) : StatsRepository {
    override suspend fun getStats(): Result<List<StatsRecord>> = dataSource.getStats().toResult()

    override suspend fun addStats(statsRecord: StatsRecord): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addStats(statsRecord.copy(date = DatetimeProvider.getCurrentDateTime()))
        }

    override suspend fun deleteStat(id: Int): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.deleteStat(id)
        }
}