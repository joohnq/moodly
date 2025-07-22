package com.joohnq.self_journal.api.entity

import com.joohnq.domain.getNow
import com.joohnq.mood.domain.entity.Mood
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecord(
    val id: Int = -1,
    val mood: Mood = Mood.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)