package com.joohnq.gratefulness.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateTimeMapper.toLocalDateTime
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.repository.GratefulnessRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GratefulnessRepositoryImpl(
    private val database: AppDatabaseSql,
) : GratefulnessRepository {
    private val query = database.gratefulnessesQueries

    override fun observe(): Flow<List<Gratefulness>> =
        query
            .getAll(gratefulnessMapper)
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun add(item: Gratefulness) {
        withContext(Dispatchers.IO) {
            query.add(
                iAmGratefulFor = item.iAmGratefulFor,
                smallThingIAppreciate = item.smallThingIAppreciate,
                description = item.description
            )
        }
    }

    override suspend fun deleteById(id: Long) {
        withContext(Dispatchers.IO) {
            query.deleteById(id)
        }
    }
}

val gratefulnessMapper: (Long, String, String, String, String) -> Gratefulness =
    { id, iAmGratefulFor, smallThingIAppreciate, description, createdAt ->
        Gratefulness(
            id = id,
            iAmGratefulFor = iAmGratefulFor,
            smallThingIAppreciate = smallThingIAppreciate,
            description = description,
            createdAt = createdAt.toLocalDateTime()
        )
    }
