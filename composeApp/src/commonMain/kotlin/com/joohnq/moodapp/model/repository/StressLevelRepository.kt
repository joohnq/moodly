package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.model.dao.StressLevelRecordDAO

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
            stressLevelRecordDAO.addStressLevel(stressLevelRecord)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}