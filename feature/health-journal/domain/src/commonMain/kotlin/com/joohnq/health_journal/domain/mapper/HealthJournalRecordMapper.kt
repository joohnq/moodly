package com.joohnq.health_journal.domain.mapper

import com.joohnq.core.ui.getNow
import com.joohnq.health_journal.domain.entity.HealthJournalRecord

fun List<HealthJournalRecord>.getTodayHealthJournalRecord(): HealthJournalRecord? =
    find { it.createdAt.date == getNow().date }