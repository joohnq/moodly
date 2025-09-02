package com.joohnq.self_journal.presentation

import androidx.compose.ui.focus.FocusRequester
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.ui.UnidirectionalViewModel

sealed interface EditSelfJournalContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data object ResetState : Intent

        data object ClearEditingState : Intent

        data object Action : Intent

        data object FreeTitleFocus : Intent

        data object FreeDescriptionFocus : Intent

        data class GetById(
            val id: Long,
        ) : Intent

        data class Delete(
            val id: Long,
        ) : Intent

        data class ChangeTitle(
            val title: String,
        ) : Intent

        data class ChangeDescription(
            val description: String,
        ) : Intent

        data class ChangeIsEditing(
            val value: Boolean,
        ) : Intent

        data class ChangeOpenDeleteDialog(
            val value: Boolean,
        ) : Intent
    }

    sealed interface SideEffect {
        data object GoBack : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val currentSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val editingSelfJournalRecord: SelfJournalRecord = SelfJournalRecord(),
        val isEditing: Boolean = false,
        val openDeleteDialog: Boolean = false,
        val canSave: Boolean = false,
        val titleFocusRequest: FocusRequester = FocusRequester(),
        val descriptionFocusRequest: FocusRequester = FocusRequester(),
    )
}
