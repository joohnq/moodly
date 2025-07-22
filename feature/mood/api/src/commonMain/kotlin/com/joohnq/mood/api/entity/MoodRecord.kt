package com.joohnq.mood.api.entity

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class MoodRecord(
    val id: Int = -1,
    val mood: Mood = Mood.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)
