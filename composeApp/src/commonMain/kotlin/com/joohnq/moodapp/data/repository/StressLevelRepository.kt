package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.converters.StressorsConverter
import com.joohnq.moodapp.data.dao.StressLevelRecordDAO
import com.joohnq.moodapp.domain.StressLevelRecord

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
            val stressorsString =
                StressorsConverter().fromStressorsList(stressLevelRecord.stressors)
            stressLevelRecordDAO.addStressLevel(stressLevelRecord.stressLevel, stressorsString)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}