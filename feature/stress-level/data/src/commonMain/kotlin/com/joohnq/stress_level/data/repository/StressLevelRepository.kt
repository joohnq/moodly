package com.joohnq.stress_level.data.repository

import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.stress_level.domain.entity.StressLevelRecord


class StressLevelRepositoryImpl(
    private val stressLevelRecordDAO: StressLevelRecordDAO
) : StressLevelRepository {
    override suspend fun getStressLevels(): List<StressLevelRecord> =
        stressLevelRecordDAO.getStressLevels()

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord) =
        try {
            stressLevelRecordDAO.addStressLevel(
                stressLevelRecord.copy(
                    date = DatetimeManager.getCurrentDateTime(),
                )
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}