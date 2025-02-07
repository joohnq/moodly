package com.joohnq.stress_level.ui.resource

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toResultResource
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.toDomain
import com.joohnq.stress_level.ui.mapper.toResource
import kotlinx.datetime.LocalDateTime

data class StressLevelRecordResource(
    val id: Int = -1,
    val stressLevel: StressLevelResource = StressLevelResource.One,
    val stressors: List<StressorResource> = emptyList(),
    val createdAt: LocalDateTime = getNow(),
)

fun StressLevelRecord.toResource(): StressLevelRecordResource = StressLevelRecordResource(
    id = id,
    stressLevel = stressLevel.toResource(),
    stressors = stressors.toResource(),
    createdAt = createdAt,
)

fun Result<List<StressLevelRecord>>.toResource(): Result<List<StressLevelRecordResource>> =
    toResultResource { it.toResource() }

fun StressLevelRecordResource.toDomain(): StressLevelRecord = StressLevelRecord(
    id = id,
    stressLevel = stressLevel.toDomain(),
    stressors = stressors.toDomain(),
    createdAt = createdAt,
)