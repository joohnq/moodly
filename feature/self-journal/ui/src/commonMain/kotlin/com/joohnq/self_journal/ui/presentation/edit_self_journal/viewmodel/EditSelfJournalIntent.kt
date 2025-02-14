package com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel

import com.joohnq.self_journal.domain.entity.SelfJournalRecord

sealed interface EditSelfJournalIntent {
    data object ResetState : EditSelfJournalIntent
    data object ClearEditingState : EditSelfJournalIntent
    data class SetEditSelfJournal(val record: SelfJournalRecord) : EditSelfJournalIntent
    data class UpdateTitle(val title: String) : EditSelfJournalIntent
    data class UpdateDescription(val description: String) : EditSelfJournalIntent
    data class UpdateIsEditing(val value: Boolean) : EditSelfJournalIntent
    data class UpdateOpenDeleteDialog(val value: Boolean) : EditSelfJournalIntent
}