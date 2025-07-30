package com.joohnq.stress_level.impl.data.repository

import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.stress_level.api.converter.StressLevelRecordConverter
import com.joohnq.stress_level.api.converter.StressorsConverter
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.repository.StressLevelRepository
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StressLevelRepositoryImpl(
    private val database: StressLevelDatabaseSql,
) : StressLevelRepository {
    private val query = database.stressLevelRecordQueries

    private val _records = MutableStateFlow<Result<List<StressLevelRecord>>>(Result.success(listOf()))

    override val records: StateFlow<Result<List<StressLevelRecord>>> = _records.asStateFlow()

    private val repositoryScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        repositoryScope.launch {
            getAll()
        }
    }

    override suspend fun getAll(): Result<List<StressLevelRecord>> {
        val result =
            executeTryCatchResult {
                query
                    .getStressLevels { id, stressLevel, stressors, createdAt ->
                        StressLevelRecord(
                            id = id.toInt(),
                            stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                            stressors = StressorsConverter.toStressorsList(stressors),
                            createdAt = LocalDateTimeConverter.toLocalDateTime(createdAt)
                        )
                    }.executeAsList()
            }
        _records.value = result
        return result
    }

    override suspend fun add(stressLevelRecord: StressLevelRecord): Result<Boolean> =
        executeTryCatchResult {
            query.addStressLevel(
                stressLevel = StressLevelRecordConverter.fromStressLevel(stressLevelRecord.stressLevel),
                stressors = StressorsConverter.fromStressorsList(stressLevelRecord.stressors)
            )
            true
        }

    override suspend fun delete(id: Int): Result<Boolean> =
        executeTryCatchResult {
            query.deleteRecord(id = id.toLong())
            true
        }
}
