package com.joohnq.health_journal.domain.entity

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime

data class HealthJournalRecord(
    val id: Int = 0,
    val mood: Mood = Mood.Neutral,
    val title: String = "",
    val description: String = "",
    val date: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
)