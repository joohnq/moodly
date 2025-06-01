package com.joohnq.self_journal.ui.resource

import com.joohnq.domain.getNow
import com.joohnq.mood.ui.resource.MoodResource
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)