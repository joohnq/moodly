package com.joohnq.mood.ui.resource

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toResultResource
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.mood.ui.mapper.toResource
import kotlinx.datetime.LocalDateTime

data class MoodRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)