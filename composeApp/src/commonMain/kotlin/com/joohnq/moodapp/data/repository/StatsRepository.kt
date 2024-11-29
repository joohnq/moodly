package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.data.dao.StatsRecordDAO
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.util.helper.DatetimeManager

interface StatsRepository {
    suspend fun getStats(): List<StatsRecord>
    suspend fun addStats(statsRecord: StatsRecord): Boolean
    suspend fun deleteStat(id: Int): Boolean
}

class StatsRepositoryImpl(
    private val statsRecordDAO: StatsRecordDAO
) : StatsRepository {

    override suspend fun getStats(): List<StatsRecord> = statsRecordDAO.getStats()

    override suspend fun addStats(statsRecord: StatsRecord): Boolean =
        try {
            statsRecordDAO.addStats(statsRecord.copy(date = DatetimeManager.getCurrentDateTime()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun deleteStat(id: Int): Boolean =
        try {
            statsRecordDAO.deleteStat(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}