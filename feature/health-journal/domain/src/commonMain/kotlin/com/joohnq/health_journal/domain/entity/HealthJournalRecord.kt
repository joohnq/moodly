package com.joohnq.health_journal.domain.entity

import com.joohnq.domain.DatetimeProvider
import com.joohnq.mood.domain.entity.Mood
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class HealthJournalRecord(
    val id: Int,
    val mood: Mood,
    val title: String,
    val description: String,
    val date: LocalDateTime,
) {
    companion object {
        fun init(): HealthJournalRecord = HealthJournalRecord(
            id = 0,
            mood = Mood.Neutral,
            title = "",
            description = "",
            date = DatetimeProvider.getCurrentDateTime(),
        )
    }
}