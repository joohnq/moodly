package com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel

import com.joohnq.self_journal.domain.entity.SelfJournalRecord

data class EditSelfJournalState(
    val currentSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
    val editingSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
    val isEditing: Boolean = false,
    val openDeleteDialog: Boolean = false,
)