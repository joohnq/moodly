package com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class SelfJournalHistoryViewModel : ViewModel() {
    private val _state = MutableStateFlow(SelfJournalHistoryState())
    val state: StateFlow<SelfJournalHistoryState> =
        _state.asStateFlow()

    fun onAction(intent: SelfJournalHistoryIntent) {
        when (intent) {
            is SelfJournalHistoryIntent.UpdateCurrentDeleteId -> updateCurrentDeleteId(intent.id)
            is SelfJournalHistoryIntent.UpdateOpenDeleteDialog -> updateOpenDeleteDialog(intent.openDeleteDialog)
            is SelfJournalHistoryIntent.UpdateSelectedDateTime -> updateSelectedDateTime(intent.selectedDateTime)
            is SelfJournalHistoryIntent.ResetState -> resetState()
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
            SelfJournalHistoryState()
        }
    }
}