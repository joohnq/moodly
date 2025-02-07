package com.joohnq.stress_level.ui.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

fun List<StressLevelRecordResource>.getTodayStressLevelRecord(): StressLevelRecordResource? =
    find { record -> record.createdAt.date == getNow().date }

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