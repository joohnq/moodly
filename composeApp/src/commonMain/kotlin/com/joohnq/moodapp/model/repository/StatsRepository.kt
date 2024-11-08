package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.model.dao.StatsRecordDAO

interface StatsRepository {
    suspend fun getStats(): List<StatsRecord>
    suspend fun addStats(statsRecord: StatsRecord): Boolean
}

class StatsRepositoryImpl(
    private val statsRecordDAO: StatsRecordDAO
) : StatsRepository {

    override suspend fun getStats(): List<StatsRecord> = statsRecordDAO.getStats()

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        try {
            statsRecordDAO.addStats(statsRecord)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}