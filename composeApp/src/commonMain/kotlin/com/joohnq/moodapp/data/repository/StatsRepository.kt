package com.joohnq.moodapp.data.repository

import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.data.dao.StatsRecordDAO

interface StatsRepository {
    suspend fun getStats(): List<StatsRecord>
    suspend fun addStats(mood: Mood, description: String): Boolean
}

class StatsRepositoryImpl(
    private val statsRecordDAO: StatsRecordDAO
) : StatsRepository {

    override suspend fun getStats(): List<StatsRecord> = statsRecordDAO.getStats()

    override suspend fun addStats(mood: Mood, description: String): Boolean =
        try {
            statsRecordDAO.addStats(mood, description)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}