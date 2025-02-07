package com.joohnq.sleep_quality.ui.resource

import com.joohnq.core.ui.entity.Time
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toResultResource
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toDomain
import com.joohnq.sleep_quality.ui.mapper.toResource
import kotlinx.datetime.LocalDate

data class SleepQualityRecordResource(
    val id: Int = -1,
    val sleepQuality: SleepQualityResource = SleepQualityResource.Fair,
    val startSleeping: Time = Time(0, 0),
    val endSleeping: Time = Time(0, 0),
    val sleepInfluences: List<SleepInfluencesResource> = emptyList(),
    val createdAt: LocalDate = getNow().date,
)

fun SleepQualityRecord.toResource(): SleepQualityRecordResource = SleepQualityRecordResource(
    id = id,
    sleepQuality = sleepQuality.toResource(),
    startSleeping = startSleeping,
    endSleeping = endSleeping,
    sleepInfluences = sleepInfluences.toResource(),
    createdAt = createdAt,
)

fun Result<List<SleepQualityRecord>>.toResource(): Result<List<SleepQualityRecordResource>> =
    toResultResource { it.toResource() }

fun SleepQualityRecordResource.toDomain(): SleepQualityRecord = SleepQualityRecord(
    id = id,
    sleepQuality = sleepQuality.toDomain(),
    startSleeping = startSleeping,
    endSleeping = endSleeping,
    sleepInfluences = sleepInfluences.toDomain(),
    createdAt = createdAt,
)