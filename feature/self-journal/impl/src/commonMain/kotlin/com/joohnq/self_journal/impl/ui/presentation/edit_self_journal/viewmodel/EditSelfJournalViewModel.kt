package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditSelfJournalViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditSelfJournalState())
    val state: StateFlow<EditSelfJournalState> =
        _state.asStateFlow()

    fun onAction(intent: EditSelfJournalIntent) {
        when (intent) {
            EditSelfJournalIntent.ResetState -> resetState()
            is EditSelfJournalIntent.UpdateDescription ->
                updateDescription(intent.description)

            is EditSelfJournalIntent.UpdateOpenDeleteDialog ->
                updateOpenDeleteDialog(intent.value)

            is EditSelfJournalIntent.UpdateTitle ->
                updateTitle(intent.title)

            is EditSelfJournalIntent.UpdateIsEditing ->
                updateIsEditing(intent.value)

            is EditSelfJournalIntent.SetEditSelfJournal ->
                setEditingSelfJournal(intent.record)

            EditSelfJournalIntent.ClearEditingState -> clearEditingState()
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

    private fun resetState() {
        _state.update { EditSelfJournalState() }
    }
}