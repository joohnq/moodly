package com.joohnq.stress_level.impl.ui.mapper

import com.joohnq.api.getNow
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import kotlinx.datetime.LocalDate

fun List<StressLevelRecordResource>.getTodayStressLevelRecord(): StressLevelRecordResource? =
    find { record -> record.createdAt.date == getNow().date }

fun List<StressLevelRecordResource>.toGroupedByDate(): Map<LocalDate, List<StressLevelRecordResource>> =
    groupBy { it.createdAt }
        .map { (key, value) ->
            key.date to value
        }.toMap()

fun StressLevelRecord.toResource(): StressLevelRecordResource = StressLevelRecordResource(
    id = id,
    stressLevel = stressLevel.toResource(),
    stressors = stressors.toResource(),
    createdAt = createdAt,
)

fun StressLevelRecordResource.toDomain(): StressLevelRecord = StressLevelRecord(
    id = id,
    stressLevel = stressLevel.toDomain(),
    stressors = stressors.toDomain(),
    createdAt = createdAt,
)