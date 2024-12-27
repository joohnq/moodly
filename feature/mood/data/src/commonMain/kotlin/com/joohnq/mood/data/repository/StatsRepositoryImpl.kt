package com.joohnq.mood.data.repository

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.repository.StatsRepository
import com.joohnq.mood.util.helper.DatetimeManager

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