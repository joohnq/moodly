package com.joohnq.self_journal.presentation

import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.ui.UnidirectionalViewModel

sealed interface EditSelfJournalContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event
    }

    sealed interface Intent {
        data object ResetState : Intent

        data object ClearEditingState : Intent

        data object Update : Intent

        data class GetById(
            val id: Int,
        ) : Intent

        data class Delete(
            val id: Int,
        ) : Intent

        data class Set(
            val record: SelfJournalRecord,
        ) : Intent

        data class UpdateTitle(
            val title: String,
        ) : Intent

        data class UpdateDescription(
            val description: String,
        ) : Intent

        data class UpdateIsEditing(
            val value: Boolean,
        ) : Intent

        data class UpdateOpenDeleteDialog(
            val value: Boolean,
        ) : Intent
    }

    sealed interface SideEffect {
        data object OnGoBack : SideEffect

        data object Updated : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val currentSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val editingSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val isEditing: Boolean = false,
        val openDeleteDialog: Boolean = false,
    )
}