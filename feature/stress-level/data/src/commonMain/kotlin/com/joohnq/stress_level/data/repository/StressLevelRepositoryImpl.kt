package com.joohnq.stress_level.data.repository

import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.stress_level.domain.data_source.StressLevelDataSource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository


class StressLevelRepositoryImpl(
    private val dataSource: StressLevelDataSource,
) : StressLevelRepository {
    override suspend fun getStressLevels(): List<StressLevelRecord> =
        dataSource.getStressLevels()

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord) =
        try {
            dataSource.addStressLevel(
                stressLevelRecord.copy(
                    date = DatetimeProvider.getCurrentDateTime(),
                )
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}