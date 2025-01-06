package com.joohnq.mood.domain.entity

import com.joohnq.core.ui.DatetimeProvider
import kotlinx.datetime.LocalDateTime

data class StatsRecord(
    val id: Int = 0,
    val mood: Mood = Mood.Neutral,
    val description: String = "",
    val date: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
)
