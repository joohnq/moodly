package com.joohnq.health_journal.ui.presentation.all_journals

import androidx.lifecycle.ViewModel
import com.joohnq.mood.util.helper.DatetimeProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

sealed class AllJournalIntent {
    data class UpdateSelectedDateTime(val selectedDateTime: LocalDate) : AllJournalIntent()
    data class UpdateOpenDeleteDialog(val openDeleteDialog: Boolean) : AllJournalIntent()
    data class UpdateCurrentDeleteId(val id: Int) : AllJournalIntent()
    data class ResetState(val id: Int) : AllJournalIntent()
}

data class AllJournalViewModelState(
    val selectedDateTime: LocalDate = DatetimeProvider.getCurrentDateTime().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)

class AllJournalViewModel : ViewModel() {
    private val _allJournalViewModelState = MutableStateFlow(AllJournalViewModelState())
    val allJournalViewModelState: StateFlow<AllJournalViewModelState> =
        _allJournalViewModelState.asStateFlow()

    fun onAction(intent: AllJournalIntent) {
        when (intent) {
            is AllJournalIntent.UpdateCurrentDeleteId -> updateCurrentDeleteId(intent.id)
            is AllJournalIntent.UpdateOpenDeleteDialog -> updateOpenDeleteDialog(intent.openDeleteDialog)
            is AllJournalIntent.UpdateSelectedDateTime -> updateSelectedDateTime(intent.selectedDateTime)
            is AllJournalIntent.ResetState -> resetState()
        }
    }

    private fun updateCurrentDeleteId(id: Int) {
        _allJournalViewModelState.update {
            it.copy(currentDeleteId = id)
        }
    }

    private fun updateOpenDeleteDialog(openDeleteDialog: Boolean) {
        _allJournalViewModelState.update {
            it.copy(openDeleteDialog = openDeleteDialog)
        }
    }

    private fun updateSelectedDateTime(selectedDateTime: LocalDate) {
        _allJournalViewModelState.update {
            it.copy(selectedDateTime = selectedDateTime)
        }
    }

    private fun resetState() {
        _allJournalViewModelState.update {
            AllJournalViewModelState()
        }
    }
}