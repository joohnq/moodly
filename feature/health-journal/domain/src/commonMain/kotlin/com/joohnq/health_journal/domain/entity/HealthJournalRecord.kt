package com.joohnq.health_journal.domain.entity

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.mood.domain.entity.Mood
import kotlinx.datetime.LocalDate

data class HealthJournalRecord(
    val id: Int = -1,
    val mood: Mood = Mood.Neutral,
    val title: String = "",
    val description: String = "",
    val date: LocalDate = DatetimeProvider.getCurrentDateTime().date,
)