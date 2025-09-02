package com.joohnq.sleep_quality.impl.ui.mapper

import com.joohnq.api.getNow
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.sleep_quality.api.entity.SleepQuality
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.mapper.SleepInfluencesResourceMapper.toDomain
import com.joohnq.sleep_quality.impl.ui.mapper.SleepInfluencesResourceMapper.toResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource.Excellent
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource.Fair
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource.Good
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource.Poor
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource.Worst

object SleepQualityResourceMapper {
    fun allSleepQualityResource(): List<SleepQualityResource> =
        listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )

    fun SleepQuality.toResource(): SleepQualityResource =
        when (this) {
            SleepQuality.Excellent -> Excellent
            SleepQuality.Good -> Good
            SleepQuality.Fair -> Fair
            SleepQuality.Poor -> Poor
            SleepQuality.Worst -> Worst
        }

    fun SleepQualityResource.toDomain(): SleepQuality =
        when (this) {
            Excellent -> SleepQuality.Excellent
            Good -> SleepQuality.Good
            Fair -> SleepQuality.Fair
            Poor -> SleepQuality.Poor
            Worst -> SleepQuality.Worst
        }

    fun SleepQualityResource.toMoodResource(): MoodResource =
        when (this) {
            Excellent -> MoodResource.Overjoyed
            Good -> MoodResource.Happy
            Fair -> MoodResource.Neutral
            Poor -> MoodResource.Sad
            Worst -> MoodResource.Depressed
        }

    fun Float.toSleepQualityResource(): SleepQualityResource =
        when (this) {
            0f -> Worst
            25f -> Poor
            50f -> Fair
            75f -> Good
            else -> Excellent
        }

    fun List<SleepQualityRecordResource>.getTodaySleepQualityRecord(): SleepQualityRecordResource? =
        find {
            it.createdAt ==
                getNow().date
        }

    fun List<SleepQualityRecordResource>.toMonthRecordsCount(): Int =
        filter {
            it.createdAt.month == getNow().date.month
        }.size

    fun SleepQualityRecordResource.toDomain(): SleepQualityRecord =
        SleepQualityRecord(
            id = id,
            quality = sleepQuality.toDomain(),
            start = startSleeping,
            end = endSleeping,
            influences = sleepInfluences.toDomain(),
            createdAt = createdAt
        )

    fun SleepQualityRecord.toResource(): SleepQualityRecordResource =
        SleepQualityRecordResource(
            id = id,
            sleepQuality = quality.toResource(),
            startSleeping = start,
            endSleeping = end,
            sleepInfluences = influences.toResource(),
            createdAt = createdAt
        )

    fun List<SleepQualityRecord>.toResource(): List<SleepQualityRecordResource> = map { it.toResource() }
}
