package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import com.joohnq.ui.BaseViewModel

class EditSelfJournalViewModel(
    initialState: EditSelfJournalContract.State = EditSelfJournalContract.State(),
) : BaseViewModel<EditSelfJournalContract.State, EditSelfJournalContract.Intent, EditSelfJournalContract.SideEffect>(
    initialState = initialState
), EditSelfJournalContract.ViewModel {
    override fun onIntent(intent: EditSelfJournalContract.Intent) {
        when (intent) {
            EditSelfJournalContract.Intent.ResetState ->
                updateState { EditSelfJournalContract.State() }

            is EditSelfJournalContract.Intent.UpdateDescription ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord = it.editingSelfJournalRecord.copy(
                            description = intent.description
                        ),
                    )
                }

            is EditSelfJournalContract.Intent.UpdateOpenDeleteDialog ->
                updateState { it.copy(openDeleteDialog = intent.value) }

            is EditSelfJournalContract.Intent.UpdateTitle ->
                updateState {
                    it.copy(
                        editingSelfJournalRecord = it.editingSelfJournalRecord.copy(title = intent.title)
                    )
                }

            is EditSelfJournalContract.Intent.UpdateIsEditing ->
                updateState { it.copy(isEditing = intent.value) }

            is EditSelfJournalContract.Intent.Set ->
                updateState {
                    it.copy(
                        currentSelfJournalRecord = intent.record,
                        editingSelfJournalRecord = intent.record
                    )
                }

            EditSelfJournalContract.Intent.ClearEditingState ->
                updateState {
                    it.copy(
                        currentSelfJournalRecord = state.value.editingSelfJournalRecord,
                        editingSelfJournalRecord = state.value.editingSelfJournalRecord
                    )
                }
        }
    }
}