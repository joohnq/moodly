package com.joohnq.health_journal.ui.presentation.all_journals.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class AllJournalViewModel : ViewModel() {
    private val _state = MutableStateFlow(AllJournalState())
    val state: StateFlow<AllJournalState> =
        _state.asStateFlow()

    fun onAction(intent: AllJournalIntent) {
        when (intent) {
            is AllJournalIntent.UpdateCurrentDeleteId -> updateCurrentDeleteId(intent.id)
            is AllJournalIntent.UpdateOpenDeleteDialog -> updateOpenDeleteDialog(intent.openDeleteDialog)
            is AllJournalIntent.UpdateSelectedDateTime -> updateSelectedDateTime(intent.selectedDateTime)
            is AllJournalIntent.ResetState -> resetState()
        }
    }

    private fun updateCurrentDeleteId(id: Int) {
        _state.update {
            it.copy(currentDeleteId = id)
        }
    }

    private fun updateOpenDeleteDialog(openDeleteDialog: Boolean) {
        _state.update {
            it.copy(openDeleteDialog = openDeleteDialog)
        }
    }

    private fun updateSelectedDateTime(selectedDateTime: LocalDate) {
        _state.update {
            it.copy(selectedDateTime = selectedDateTime)
        }
    }

    private fun resetState() {
        _state.update {
            AllJournalState()
        }
    }
}