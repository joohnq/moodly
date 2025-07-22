package com.joohnq.mood.impl.ui.resource

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class MoodRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)