package com.joohnq.health_journal.ui.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

sealed interface HealthJournalIntent {
    data object GetHealthJournals : HealthJournalIntent
    data class AddHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent

    data class UpdateHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent

    data class DeleteHealthJournal(val id: Int) : HealthJournalIntent
}
