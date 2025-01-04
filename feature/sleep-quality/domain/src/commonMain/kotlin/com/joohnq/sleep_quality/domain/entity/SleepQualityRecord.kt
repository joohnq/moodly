package com.joohnq.sleep_quality.domain.entity

import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime

data class SleepQualityRecord(
    val id: Int = 0,
    val sleepQuality: SleepQuality = SleepQuality.Fair,
    val startSleeping: String = "--:--",
    val endSleeping: String = "--:--",
    val sleepInfluences: List<SleepInfluences> = emptyList(),
    val date: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
) {
    companion object {
        fun SleepQualityRecord.startSleeping(hour: Int, minute: Int): SleepQualityRecord =
            this.copy(
                startSleeping = DatetimeProvider.formatTime(hour, minute)
            )

        fun SleepQualityRecord.endSleeping(hour: Int, minute: Int): SleepQualityRecord =
            this.copy(
                endSleeping = DatetimeProvider.formatTime(hour, minute)
            )
    }
}