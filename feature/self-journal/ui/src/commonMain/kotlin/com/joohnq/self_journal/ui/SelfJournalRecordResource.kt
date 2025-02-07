package com.joohnq.self_journal.ui

import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toResultResource
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.mood.ui.resource.toResource
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
)

fun SelfJournalRecord.toResource(): SelfJournalRecordResource = SelfJournalRecordResource(
    id = id,
    mood = mood.toResource(),
    title = title,
    description = description,
    createdAt = createdAt,
)

fun Result<List<SelfJournalRecord>>.toResource(): Result<List<SelfJournalRecordResource>> =
    toResultResource { it.toResource() }
