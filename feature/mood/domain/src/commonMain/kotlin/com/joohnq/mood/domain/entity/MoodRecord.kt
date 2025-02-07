package com.joohnq.mood.domain.entity

import com.joohnq.core.ui.getNow
import kotlinx.datetime.LocalDateTime

data class MoodRecord(
    val id: Int = -1,
    val mood: Mood = Mood.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)
