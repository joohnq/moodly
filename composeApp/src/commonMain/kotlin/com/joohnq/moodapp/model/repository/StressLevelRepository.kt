package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.model.converters.StressorsConverter
import com.joohnq.moodapp.model.dao.StressLevelRecordDAO

interface StressLevelRepository {
    suspend fun getStressLevels(): List<StressLevelRecord>
    suspend fun addStressLevel(stressLevel: StressLevel, stressors: List<Stressors>): Boolean
}

class StressLevelRepositoryImpl(
    private val stressLevelRecordDAO: StressLevelRecordDAO
) : StressLevelRepository {
    override suspend fun getStressLevels(): List<StressLevelRecord> =
        stressLevelRecordDAO.getStressLevels()

    override suspend fun addStressLevel(stressLevel: StressLevel, stressors: List<Stressors>) =
        try {
            val stressorsString = StressorsConverter().fromStressorsList(stressors)
            stressLevelRecordDAO.addStressLevel(stressLevel, stressorsString)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}