package com.joohnq.stress_level.data.repository

import com.joohnq.domain.DatetimeProvider
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import org.koin.core.annotation.Single

@Single(binds = [StressLevelRepository::class])
class StressLevelRepositoryImpl(
    private val stressLevelRecordDAO: StressLevelRecordDAO
) : StressLevelRepository {
    override suspend fun getStressLevels(): List<StressLevelRecord> =
        stressLevelRecordDAO.getStressLevels()

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord) =
        try {
            stressLevelRecordDAO.addStressLevel(
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