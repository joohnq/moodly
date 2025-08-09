package com.joohnq.gratefulness.api.repository

import com.joohnq.gratefulness.api.entity.Gratefulness
import kotlinx.coroutines.flow.Flow

interface GratefulnessRepository {
    fun observe(): Flow<List<Gratefulness>>

    suspend fun add(item: Gratefulness)

    suspend fun delete(id: Int)
}
