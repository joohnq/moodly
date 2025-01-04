package com.joohnq.stress_level.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.domain.converter.StressLevelRecordConverter
import com.joohnq.stress_level.domain.converter.StressorsConverter
import com.joohnq.stress_level.domain.data_source.StressLevelDataSource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class StressLevelDataSourceImpl(private val database: StressLevelDatabaseSql) :
    StressLevelDataSource {
    private val query = database.stressLevelRecordQueries

    override suspend fun getStressLevels(): List<StressLevelRecord> =
        withContext(Dispatchers.IO) {
            query.getStressLevels { id, stressLevel, stressors, date ->
                StressLevelRecord(
                    id = id.toInt(),
                    stressLevel = StressLevelRecordConverter.toStressLevel(stressLevel),
                    stressors = StressorsConverter.toStressorsList(stressors),
                    date = LocalDateTimeConverter.toLocalDateTime(date)
                )
            }.executeAsList()
        }

    override suspend fun addStressLevel(stressLevelRecord: StressLevelRecord) =
        withContext(Dispatchers.IO) {
            query.addStressLevel(
                stressLevel = StressLevelRecordConverter.fromStressLevel(stressLevelRecord.stressLevel),
                stressors = StressorsConverter.fromStressorsList(stressLevelRecord.stressors)
            )
        }
}