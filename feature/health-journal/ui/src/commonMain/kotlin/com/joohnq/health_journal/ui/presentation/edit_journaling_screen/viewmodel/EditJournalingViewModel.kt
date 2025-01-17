package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditJournalingViewModel : ViewModel() {
    private val _state = MutableStateFlow(EditJournalingState())
    val state: StateFlow<EditJournalingState> =
        _state.asStateFlow()

    fun onAction(intent: EditJournalingIntent) {
        when (intent) {
            EditJournalingIntent.ResetState ->
                resetState()

            is EditJournalingIntent.UpdateDescription ->
                updateDescription(intent.description)

            is EditJournalingIntent.UpdateOpenDeleteDialog ->
                updateOpenDeleteDialog(intent.value)

            is EditJournalingIntent.UpdateTitle ->
                updateTitle(intent.title)

            is EditJournalingIntent.UpdateIsEditing ->
                updateIsEditing(intent.value)

            is EditJournalingIntent.SetEditJournaling ->
                setEditingHealthJournal(intent.healthJournalRecord)
        }
    }

    private fun setEditingHealthJournal(healthJournalRecord: HealthJournalRecord) {
        _state.update {
            it.copy(
                currentHealthJournalRecord = healthJournalRecord,
                editingHealthJournalRecord = healthJournalRecord
            )
        }
    }

    private fun updateTitle(title: String) {
        _state.update {
            it.copy(
                editingHealthJournalRecord = it.editingHealthJournalRecord.copy(title = title)
            )
        }
    }

    private fun updateIsEditing(value: Boolean) {
        _state.update { it.copy(isEditing = value) }
    }

    private fun updateDescription(description: String) {
        _state.update {
            it.copy(
                editingHealthJournalRecord = it.editingHealthJournalRecord.copy(
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
        _state.update { EditJournalingState() }
    }
}