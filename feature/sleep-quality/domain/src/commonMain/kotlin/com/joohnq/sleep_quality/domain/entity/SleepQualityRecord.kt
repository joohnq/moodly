package com.joohnq.sleep_quality.domain.entity

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.entity.Time
import kotlinx.datetime.LocalDate

data class SleepQualityRecord(
    val id: Int = -1,
    val sleepQuality: SleepQuality = SleepQuality.Fair,
    val startSleeping: Time,
    val endSleeping: Time,
    val sleepInfluences: List<SleepInfluences> = emptyList(),
    val createdAt: LocalDate = DatetimeProvider.getCurrentDateTime().date,
)