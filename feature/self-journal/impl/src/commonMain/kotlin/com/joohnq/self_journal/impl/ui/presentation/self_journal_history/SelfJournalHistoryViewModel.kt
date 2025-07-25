package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SelfJournalHistoryViewModel : ViewModel() {
    private val _state = MutableStateFlow(SelfJournalHistoryContract.State())
    val state: StateFlow<SelfJournalHistoryContract.State> =
        _state.asStateFlow()

    fun onAction(intent: SelfJournalHistoryContract.Intent) {
        when (intent) {
            is SelfJournalHistoryContract.Intent.UpdateCurrentDeleteId ->
                _state.update { it.copy(currentDeleteId = intent.id) }

            is SelfJournalHistoryContract.Intent.UpdateOpenDeleteDialog ->
                _state.update { it.copy(openDeleteDialog = intent.openDeleteDialog) }


            is SelfJournalHistoryContract.Intent.UpdateSelectedDateTime ->
                _state.update { it.copy(selectedDateTime = intent.selectedDateTime) }


            is SelfJournalHistoryContract.Intent.ResetState ->
                _state.update { SelfJournalHistoryContract.State() }
        }
    }
}