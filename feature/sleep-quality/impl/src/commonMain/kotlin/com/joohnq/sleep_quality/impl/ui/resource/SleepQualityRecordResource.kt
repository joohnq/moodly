package com.joohnq.sleep_quality.impl.ui.resource

import com.joohnq.api.entity.Time
import com.joohnq.api.getNow
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource.Companion.allSleepInfluences
import kotlinx.datetime.LocalDate

data class SleepQualityRecordResource(
    val id: Int = -1,
    val sleepQuality: SleepQualityResource = SleepQualityResource.Fair,
    val startSleeping: Time = Time(0, 0),
    val endSleeping: Time = Time(0, 0),
    val sleepInfluences: List<SleepInfluencesResource> = emptyList(),
    val createdAt: LocalDate = getNow().date,
) {
    companion object {
        val sleepQualityRecordWorstPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Worst.id,
            sleepQuality = SleepQualityResource.Worst,
        )

        val sleepQualityRecordPoorPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Fair.id,
            sleepQuality = SleepQualityResource.Poor,
        )

        val sleepQualityRecordFairPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Worst.id,
            sleepQuality = SleepQualityResource.Fair,
        )

        val sleepQualityRecordGoodPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Worst.id,
            sleepQuality = SleepQualityResource.Good,
        )

        val sleepQualityRecordExcellentPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Worst.id,
            sleepQuality = SleepQualityResource.Excellent,
        )

        val sleepQualityRecordWithSleepInfluencesPreview = SleepQualityRecordResource(
            id = SleepQualityResource.Worst.id,
            sleepQuality = SleepQualityResource.Excellent,
            sleepInfluences = allSleepInfluences
        )

        val allSleepQualityRecordResource = listOf(
            sleepQualityRecordWorstPreview,
            sleepQualityRecordPoorPreview,
            sleepQualityRecordFairPreview,
            sleepQualityRecordGoodPreview,
            sleepQualityRecordExcellentPreview,
            sleepQualityRecordWithSleepInfluencesPreview,
        )
    }
}
