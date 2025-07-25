package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import androidx.lifecycle.ViewModel
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditSelfJournalViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditSelfJournalContract.State())
    val state: StateFlow<EditSelfJournalContract.State> =
        _state.asStateFlow()

    fun onAction(intent: EditSelfJournalContract.Intent) {
        when (intent) {
            EditSelfJournalContract.Intent.ResetState -> _state.update { EditSelfJournalContract.State() }
            is EditSelfJournalContract.Intent.UpdateDescription ->
                updateDescription(intent.description)

            is EditSelfJournalContract.Intent.UpdateOpenDeleteDialog ->
                updateOpenDeleteDialog(intent.value)

            is EditSelfJournalContract.Intent.UpdateTitle ->
                updateTitle(intent.title)

            is EditSelfJournalContract.Intent.UpdateIsEditing ->
                updateIsEditing(intent.value)

            is EditSelfJournalContract.Intent.Set ->
                setEditingSelfJournal(intent.record)

            EditSelfJournalContract.Intent.ClearEditingState -> clearEditingState()
        }
    }

    private fun clearEditingState() {
        _state.update {
            it.copy(
                currentSelfJournalRecord = state.value.editingSelfJournalRecord,
                editingSelfJournalRecord = state.value.editingSelfJournalRecord
            )
        }
    }

    private fun setEditingSelfJournal(record: SelfJournalRecord) {
        _state.update {
            it.copy(
                currentSelfJournalRecord = record,
                editingSelfJournalRecord = record
            )
        }
    }

    private fun updateTitle(title: String) {
        _state.update {
            it.copy(
                editingSelfJournalRecord = it.editingSelfJournalRecord.copy(title = title)
            )
        }
    }

    private fun updateIsEditing(value: Boolean) {
        _state.update { it.copy(isEditing = value) }
    }

    private fun updateDescription(description: String) {
        _state.update {
            it.copy(
                editingSelfJournalRecord = it.editingSelfJournalRecord.copy(
                    description = description
                ),
            )
        }
    }

    private fun updateOpenDeleteDialog(value: Boolean) {
        _state.update {
            it.copy(
                openDeleteDialog = value
            )
        }
    }
}