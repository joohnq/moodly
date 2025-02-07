package com.joohnq.sleep_quality.ui.resource

import com.joohnq.core.ui.entity.Time
import com.joohnq.core.ui.getNow
import kotlinx.datetime.LocalDate

data class SleepQualityRecordResource(
    val id: Int = -1,
    val sleepQuality: SleepQualityResource = SleepQualityResource.Fair,
    val startSleeping: Time = Time(0, 0),
    val endSleeping: Time = Time(0, 0),
    val sleepInfluences: List<SleepInfluencesResource> = emptyList(),
    val createdAt: LocalDate = getNow().date,
)
