package com.joohnq.mood.api.repository

import com.joohnq.mood.api.entity.MoodRecord
import kotlinx.coroutines.flow.StateFlow

interface MoodRepository {
    val moodRecords: StateFlow<Result<List<MoodRecord>>>

    suspend fun getMoodRecords(): Result<List<MoodRecord>>

    suspend fun addMoodRecord(record: MoodRecord): Result<Boolean>

    suspend fun deleteMoodRecord(id: Int): Result<Boolean>
}
