package com.joohnq.health_journal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import com.joohnq.shared.ui.state.UiState
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HealthJournalViewModel(
    private val getHealthJournalsUseCase: GetHealthJournalsUseCase,
    private val deleteHealthJournalsUseCase: DeleteHealthJournalsUseCase,
    private val updateHealthJournalsUseCase: UpdateHealthJournalsUseCase,
    private val addHealthJournalsUseCase: AddHealthJournalsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HealthJournalState())
    val state: StateFlow<HealthJournalState> = _state.asStateFlow()

    fun onAction(intent: HealthJournalIntent) {
        when (intent) {
            is HealthJournalIntent.GetHealthJournals -> getHealthJournals()
            is HealthJournalIntent.AddHealthJournal -> addHealthJournal(intent.healthJournalRecord)
            is HealthJournalIntent.DeleteHealthJournal -> deleteHealthJournal(intent.id)
            is HealthJournalIntent.UpdateHealthJournal -> updateHealthJournal(intent.healthJournalRecord)
            HealthJournalIntent.ResetDeletingStatus -> changeDeletingStatus(UiState.Idle)
            HealthJournalIntent.ResetEditingStatus -> changeEditingStatus(UiState.Idle)
            HealthJournalIntent.ResetAddingState -> changeAddingStatus(UiState.Idle)
            is HealthJournalIntent.SetHealthJournalStateForTesting -> setHealthJournalStateForTesting(
                intent.value
            )
        }
    }

    private fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ) = viewModelScope.launch {
        changeAddingStatus(UiState.Loading)

        val res = addHealthJournalsUseCase(healthJournalRecord)
        changeAddingStatus(if (res) UiState.Success(res) else UiState.Error("Fail to add health journal"))
    }

    private fun updateHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ) = viewModelScope.launch {
        changeEditingStatus(UiState.Loading)

        val res = updateHealthJournalsUseCase(healthJournalRecord)
        changeEditingStatus(if (res) UiState.Success(res) else UiState.Error("Fail to update health journal"))
    }

    private fun getHealthJournals() =
        viewModelScope.launch {
            _state.update { it.copy(healthJournalRecords = UiState.Loading) }

            try {
                val res = getHealthJournalsUseCase()
                _state.update { it.copy(healthJournalRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _state.update { it.copy(healthJournalRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun deleteHealthJournal(id: Int) =
        viewModelScope.launch {
            changeDeletingStatus(UiState.Loading)

            val res = deleteHealthJournalsUseCase(id)

            changeDeletingStatus(if (res) UiState.Success(true) else UiState.Error("Fail to delete"))

            if (res)
                _state.update {
                    it.copy(
                        healthJournalRecords = UiState.Success(
                            it.healthJournalRecords.getValue()
                                .filter { item -> item.id != id }
                        )
                    )
                }
        }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }

    private fun changeEditingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(editing = status) }
    }

    private fun changeDeletingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(deleting = status) }
    }

    private fun setHealthJournalStateForTesting(state: HealthJournalState) {
        _state.update { state }
    }
}