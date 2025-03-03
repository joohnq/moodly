package com.joohnq.sleep_quality.domain.entity

import com.joohnq.domain.entity.Time
import com.joohnq.domain.getNow
import kotlinx.datetime.LocalDate

data class SleepQualityRecord(
    val id: Int = -1,
    val sleepQuality: SleepQuality = SleepQuality.Fair,
    val startSleeping: Time = Time(0, 0),
    val endSleeping: Time = Time(0, 0),
    val sleepInfluences: List<SleepInfluences> = emptyList(),
    val createdAt: LocalDate = getNow().date,
)