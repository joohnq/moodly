package com.joohnq.self_journal.impl.ui.resource

import com.joohnq.api.getNow
import com.joohnq.mood.impl.ui.resource.MoodResource
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)