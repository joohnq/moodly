package com.joohnq.gratefulness.impl.data.repository

import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.entity.dao.GratefulnessDao
import com.joohnq.gratefulness.api.mapper.GratefulnessMapper.toDomain
import com.joohnq.gratefulness.api.mapper.GratefulnessMapper.toDto
import com.joohnq.gratefulness.api.repository.GratefulnessRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GratefulnessRepositoryImpl(
    private val dao: GratefulnessDao,
) : GratefulnessRepository {
    override fun observe(): Flow<List<Gratefulness>> =
        dao
            .observe()
            .map { list -> list.map { it.toDomain() } }

    override suspend fun add(item: Gratefulness) {
        dao.add(item.toDto())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }
}
