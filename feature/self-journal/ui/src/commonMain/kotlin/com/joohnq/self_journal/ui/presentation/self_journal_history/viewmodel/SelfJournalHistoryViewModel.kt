package com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class SelfJournalHistoryViewModel : ViewModel() {
    private val _state = MutableStateFlow(SelfJournalHistoryContract.State())
    val state: StateFlow<SelfJournalHistoryContract.State> =
        _state.asStateFlow()

    fun onIntent(intent: SelfJournalHistoryContract.Intent) {
        when (intent) {
            is SelfJournalHistoryContract.Intent.UpdateCurrentDeleteId -> updateCurrentDeleteId(
                intent.id
            )

            is SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog -> updateOpenDeleteDialog(
                intent.openDeleteDialog
            )

            is SelfJournalHistoryContract.Intent.UpdateSelectedDateTime -> updateSelectedDateTime(
                intent.selectedDateTime
            )

            is SelfJournalHistoryContract.Intent.ResetState -> resetState()
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
            SelfJournalHistoryContract.State()
        }
    }
}