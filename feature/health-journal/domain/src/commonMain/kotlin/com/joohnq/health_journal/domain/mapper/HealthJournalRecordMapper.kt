package com.joohnq.health_journal.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.datetime.LocalDate

fun List<HealthJournalRecord>.getTodayHealthJournalRecord(): HealthJournalRecord? =
    find { it.createdAt.date == getNow().date }

fun Map<LocalDate, List<HealthJournalRecord>?>.getItemsByDate(date: LocalDate): List<HealthJournalRecord>? {
    val key = keys.find { it == date } ?: keys.last()
    return this[key]
}