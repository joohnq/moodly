package com.joohnq.health_journal.ui.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

sealed class HealthJournalIntent {
    data object GetHealthJournals : HealthJournalIntent()
    data class AddHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent()

    data class UpdateHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent()

    data object ResetDeletingStatus : HealthJournalIntent()
    data object ResetEditingStatus : HealthJournalIntent()
    data object ResetAddingState : HealthJournalIntent()
    data class DeleteHealthJournal(val id: Int) : HealthJournalIntent()
    data class SetHealthJournalStateForTesting(val value: HealthJournalState) :
        HealthJournalIntent()
}
