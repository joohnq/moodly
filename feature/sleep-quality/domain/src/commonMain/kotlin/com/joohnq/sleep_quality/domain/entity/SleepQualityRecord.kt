package com.joohnq.sleep_quality.domain.entity

import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class SleepQualityRecord(
    val id: Int,
    val sleepQuality: SleepQuality,
    val startSleeping: String,
    val endSleeping: String,
    val sleepInfluences: List<SleepInfluences>,
    val date: LocalDateTime,
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

        fun init(): SleepQualityRecord = SleepQualityRecord(
            id = 0,
            sleepQuality = SleepQuality.Fair,
            startSleeping = "--:--",
            endSleeping = "--:--",
            sleepInfluences = emptyList(),
            date = DatetimeProvider.getCurrentDateTime(),
        )
    }
}