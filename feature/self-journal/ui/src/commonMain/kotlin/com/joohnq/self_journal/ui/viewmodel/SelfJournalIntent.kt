package com.joohnq.self_journal.ui.viewmodel

import com.joohnq.self_journal.domain.entity.SelfJournalRecord

sealed interface SelfJournalIntent {
    data object GetAll : SelfJournalIntent
    data class Add(val record: SelfJournalRecord) :
        SelfJournalIntent

    data class Update(val record: SelfJournalRecord) :
        SelfJournalIntent

    data class Delete(val id: Int) : SelfJournalIntent
}
