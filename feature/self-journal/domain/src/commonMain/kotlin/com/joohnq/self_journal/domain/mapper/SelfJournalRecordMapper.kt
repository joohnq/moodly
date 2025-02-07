package com.joohnq.self_journal.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.datetime.LocalDate

fun List<SelfJournalRecord>.getTodaySelfJournalRecord(): SelfJournalRecord? =
    find { it.createdAt.date == getNow().date }

fun Map<LocalDate, List<SelfJournalRecord>?>.getItemsByDate(date: LocalDate): List<SelfJournalRecord>? {
    val key = keys.find { it == date } ?: keys.last()
    return this[key]
}