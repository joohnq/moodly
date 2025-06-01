package com.joohnq.mood.domain.repository

import com.joohnq.mood.domain.entity.MoodRecord

interface MoodRepository {
    suspend fun getMoodRecords(): Result<List<MoodRecord>>
    suspend fun addMoodRecord(record: MoodRecord): Result<Boolean>
    suspend fun deleteMoodRecord(id: Int): Result<Boolean>
}