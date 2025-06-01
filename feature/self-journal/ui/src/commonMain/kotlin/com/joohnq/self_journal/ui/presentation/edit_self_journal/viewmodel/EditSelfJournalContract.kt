package com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel

import com.joohnq.self_journal.domain.entity.SelfJournalRecord

sealed interface EditSelfJournalContract {
    sealed interface Intent: EditSelfJournalContract {
        data object ResetState : Intent
        data object ClearEditingState : Intent
        data class UpdateSelfJournal(val record: SelfJournalRecord) : Intent
        data class UpdateTitle(val title: String) : Intent
        data class UpdateDescription(val description: String) : Intent
        data class UpdateIsEditing(val value: Boolean) : Intent
        data class UpdateOpenDeleteDialog(val value: Boolean) : Intent
    }

    data class State(
        val currentSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val editingSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val isEditing: Boolean = false,
        val openDeleteDialog: Boolean = false,
    ): EditSelfJournalContract

    sealed interface Event: EditSelfJournalContract {
        data object GoBack : Event
        data object Save : Event
    }
}