package com.joohnq.mood.api.repository

import com.joohnq.mood.api.entity.MoodRecord
import kotlinx.coroutines.flow.StateFlow

interface MoodRepository {
    val records: StateFlow<Result<List<MoodRecord>>>

    suspend fun getAll(): Result<List<MoodRecord>>

    suspend fun add(record: MoodRecord): Result<Boolean>

    suspend fun delete(id: Int): Result<Boolean>
}
