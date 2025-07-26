package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.ui.UnidirectionalViewModel

sealed interface EditSelfJournalContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event

        data object OnSave : Event
    }

    sealed interface Intent {
        data object ResetState : Intent

        data object ClearEditingState : Intent

        data class Set(
            val record: SelfJournalRecord
        ) : Intent

        data class UpdateTitle(
            val title: String
        ) : Intent

        data class UpdateDescription(
            val description: String
        ) : Intent

        data class UpdateIsEditing(
            val value: Boolean
        ) : Intent

        data class UpdateOpenDeleteDialog(
            val value: Boolean
        ) : Intent
    }

    sealed interface SideEffect

    data class State(
        val currentSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val editingSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val isEditing: Boolean = false,
        val openDeleteDialog: Boolean = false
    )
}
