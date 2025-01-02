package com.joohnq.stress_level.domain.entity

import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class StressLevelRecord(
    val id: Int,
    val stressLevel: StressLevel,
    val stressors: List<Stressor>,
    val date: LocalDateTime,
) {
    companion object {
        fun init(): StressLevelRecord = StressLevelRecord(
            id = 0,
            stressLevel = StressLevel.Three,
            stressors = emptyList(),
            date = DatetimeProvider.getCurrentDateTime()
        )
    }
}
