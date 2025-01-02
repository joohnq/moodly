package com.joohnq.mood.domain.entity

import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class StatsRecord(
    val id: Int,
    val mood: Mood,
    val description: String,
    val date: LocalDateTime,
) {
    companion object {
        fun init(): StatsRecord = StatsRecord(
            id = 0,
            mood = Mood.Neutral,
            description = "",
            date = DatetimeProvider.getCurrentDateTime(),
        )
    }
}
