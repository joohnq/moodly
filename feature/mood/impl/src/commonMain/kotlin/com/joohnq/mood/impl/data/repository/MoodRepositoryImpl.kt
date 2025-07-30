package com.joohnq.mood.impl.data.repository

import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.mood.api.converter.MoodRecordConverter
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodRepositoryImpl(
    private val database: MoodDatabaseSql,
) : MoodRepository {
    private val query = database.moodRecordQueries

    private val _moodRecords = MutableStateFlow<Result<List<MoodRecord>>>(Result.success(listOf()))

    override val moodRecords: StateFlow<Result<List<MoodRecord>>> = _moodRecords.asStateFlow()

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        repositoryScope.launch {
            getMoodRecords()
        }
    }

    override suspend fun getMoodRecords(): Result<List<MoodRecord>> {
        val result =
            executeTryCatchResult {
                query
                    .getMoodRecords { id, mood, description, createdAt ->
                        MoodRecord(
                            id = id.toInt(),
                            mood = MoodRecordConverter.toMood(mood),
                            description = description,
                            createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                        )
                    }.executeAsList()
            }

        _moodRecords.value = result
        return result
    }

    override suspend fun addMoodRecord(record: MoodRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addMoodRecord(
                mood = MoodRecordConverter.fromMood(record.mood),
                description = record.description
            )
            loadInitialData()
            true
        }

    override suspend fun deleteMoodRecord(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteMoodRecord(id.toLong())
            loadInitialData()
            true
        }
}
