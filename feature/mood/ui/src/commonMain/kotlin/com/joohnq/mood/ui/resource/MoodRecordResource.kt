package com.joohnq.mood.ui.resource

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toResultResource
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.mood.ui.mapper.toResource
import kotlinx.datetime.LocalDateTime

data class MoodRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)

fun MoodRecord.toResource(): MoodRecordResource =
    MoodRecordResource(
        id = id,
        mood = mood.toResource(),
        description = description,
        createdAt = createdAt,
    )

fun Result<List<MoodRecord>>.toResource(): Result<List<MoodRecordResource>> =
    toResultResource { it.toResource() }

fun MoodRecordResource.toDomain(): MoodRecord = MoodRecord(
    id = id,
    mood = mood.toDomain(),
    description = description,
    createdAt = createdAt,
)

fun List<MoodRecordResource>.toDomain(): List<MoodRecord> = map { it.toDomain() }
