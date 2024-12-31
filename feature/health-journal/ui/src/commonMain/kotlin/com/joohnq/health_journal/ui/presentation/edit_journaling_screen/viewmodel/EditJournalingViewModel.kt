package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EditJournalingViewModel : ViewModel() {
    private val _editJournalingState = MutableStateFlow(EditJournalingState())
    val editJournalingState: StateFlow<EditJournalingState> =
        _editJournalingState.asStateFlow()

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

            is EditJournalingIntent.GetEditJournaling ->
                getEditingHealthJournal(intent.id, intent.healthJournalRecords)
        }
    }

    private fun getEditingHealthJournal(id: Int, healthJournalRecords: List<HealthJournalRecord>) {
        val healthJournal =
            healthJournalRecords.find { it.id == id } ?: return

        _editJournalingState.update {
            it.copy(
                currentHealthJournalRecord = healthJournal,
                editingHealthJournalRecord = healthJournal
            )
        }
    }

    private fun updateTitle(title: String) {
        _editJournalingState.update {
            it.copy(
                editingHealthJournalRecord = it.editingHealthJournalRecord.copy(title = title)
            )
        }
    }

    private fun updateIsEditing(value: Boolean) {
        _editJournalingState.update { it.copy(isEditing = value) }
    }

    private fun updateDescription(description: String) {
        _editJournalingState.update {
            it.copy(
                editingHealthJournalRecord = it.editingHealthJournalRecord.copy(
                    description = description
                ),
            )
        }
    }

    private fun updateOpenDeleteDialog(value: Boolean) {
        _editJournalingState.update {
            it.copy(
                openDeleteDialog = value
            )
        }
    }

    private fun resetState() {
        _editJournalingState.update { EditJournalingState() }
    }
}