package com.joohnq.sleep_quality.api.entity

import com.joohnq.api.entity.Time
import com.joohnq.api.getNow
import kotlinx.datetime.LocalDate

data class SleepQualityRecord(
    val id: Long = -1,
    val quality: SleepQuality = SleepQuality.Fair,
    val start: Time = Time(0, 0),
    val end: Time = Time(0, 0),
    val influences: List<SleepInfluences> = emptyList(),
    val createdAt: LocalDate = getNow().date,
)
