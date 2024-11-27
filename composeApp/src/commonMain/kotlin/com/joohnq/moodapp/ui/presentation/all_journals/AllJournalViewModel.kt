package com.joohnq.moodapp.ui.presentation.all_journals

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.util.helper.DatetimeManager
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

data class AllJournalState(
    val selectedDateTime: LocalDate = DatetimeManager.getCurrentDateTime().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)

class AllJournalViewModel : ViewModel() {
    private val _allJournalState = MutableStateFlow(AllJournalState())
    val allJournalState: StateFlow<AllJournalState> = _allJournalState.asStateFlow()

    fun onAction(intent: AllJournalIntent) {
        when (intent) {
            is AllJournalIntent.UpdateCurrentDeleteId -> updateCurrentDeleteId(intent.id)
            is AllJournalIntent.UpdateOpenDeleteDialog -> updateOpenDeleteDialog(intent.openDeleteDialog)
            is AllJournalIntent.UpdateSelectedDateTime -> updateSelectedDateTime(intent.selectedDateTime)
            is AllJournalIntent.ResetState -> resetState()
        }
    }

    private fun updateCurrentDeleteId(id: Int) {
        _allJournalState.update {
            it.copy(currentDeleteId = id)
        }
    }

    private fun updateOpenDeleteDialog(openDeleteDialog: Boolean) {
        _allJournalState.update {
            it.copy(openDeleteDialog = openDeleteDialog)
        }
    }

    private fun updateSelectedDateTime(selectedDateTime: LocalDate) {
        _allJournalState.update {
            it.copy(selectedDateTime = selectedDateTime)
        }
    }

    private fun resetState() {
        _allJournalState.update {
            AllJournalState()
        }
    }
}