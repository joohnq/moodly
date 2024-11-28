package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.StressLevelRecordDAO
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.util.helper.DatetimeManager

interface StressLevelRepository {
    suspend fun getStressLevels(): List<StressLevelRecord>
    suspend fun addStressLevel(stressLevelRecord: StressLevelRecord): Boolean
}

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