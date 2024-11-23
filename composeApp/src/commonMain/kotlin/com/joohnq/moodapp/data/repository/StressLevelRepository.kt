package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.data.converters.StressorsConverter
import com.joohnq.moodapp.data.dao.StressLevelRecordDAO

interface StressLevelRepository {
    suspend fun getStressLevels(): List<StressLevelRecord>
    suspend fun addStressLevel(stressLevel: StressLevel, stressors: List<Stressor>): Boolean
}

class StressLevelRepositoryImpl(
    private val stressLevelRecordDAO: StressLevelRecordDAO
) : StressLevelRepository {
    override suspend fun getStressLevels(): List<StressLevelRecord> =
        stressLevelRecordDAO.getStressLevels()

    override suspend fun addStressLevel(stressLevel: StressLevel, stressors: List<Stressor>) =
        try {
            val stressorsString = StressorsConverter().fromStressorsList(stressors)
            stressLevelRecordDAO.addStressLevel(stressLevel, stressorsString)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}