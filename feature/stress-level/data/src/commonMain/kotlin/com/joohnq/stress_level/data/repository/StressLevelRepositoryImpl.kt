package com.joohnq.stress_level.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.toResult
import com.joohnq.stress_level.domain.data_source.StressLevelDataSource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository

class StressLevelRepositoryImpl(
    private val dataSource: StressLevelDataSource,
) : StressLevelRepository {
    override suspend fun getStressLevels(): Result<List<StressLevelRecord>> =
        dataSource.getStressLevels().toResult()

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addStressLevel(
                stressLevelRecord.copy(
                    date = DatetimeProvider.getCurrentDateTime(),
                )
            )
        }
}