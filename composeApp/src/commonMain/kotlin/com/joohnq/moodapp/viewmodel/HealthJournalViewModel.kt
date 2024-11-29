package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.HealthJournalRepository
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HealthJournalIntent {
    data object GetHealthJournals : HealthJournalIntent()
    data class AddHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent()

    data class UpdateHealthJournal(val healthJournalRecord: HealthJournalRecord) :
        HealthJournalIntent()

    data object ResetDeletingStatus : HealthJournalIntent()
    data object ResetEditingStatus : HealthJournalIntent()
    data object ResetAddingState : HealthJournalIntent()
    data class DeleteHealthJournal(val id: Int) : HealthJournalIntent()
    data class SetHealthJournalStateForTesting(val value: HealthJournalState) :
        HealthJournalIntent()
}

data class HealthJournalState(
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val editing: UiState<Boolean> = UiState.Idle,
    val deleting: UiState<Boolean> = UiState.Idle,
)

class HealthJournalViewModel(
    private val healthJournalRepository: HealthJournalRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _healthJournalState = MutableStateFlow(HealthJournalState())
    val healthJournalState: StateFlow<HealthJournalState> = _healthJournalState.asStateFlow()

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
        healthJournalRecord: HealthJournalRecord
    ) = viewModelScope.launch(dispatcher) {
        changeAddingStatus(UiState.Loading)

        val res =
            healthJournalRepository.addHealthJournal(healthJournalRecord)
        changeAddingStatus(if (res) UiState.Success(res) else UiState.Error("Fail to add health journal"))
    }

    private fun updateHealthJournal(
        healthJournalRecord: HealthJournalRecord
    ) = viewModelScope.launch(dispatcher) {
        changeEditingStatus(UiState.Loading)

        val res =
            healthJournalRepository.updateHealthJournal(healthJournalRecord)
        changeEditingStatus(if (res) UiState.Success(res) else UiState.Error("Fail to update health journal"))
    }

    private fun getHealthJournals() =
        viewModelScope.launch(dispatcher) {
            _healthJournalState.update { it.copy(healthJournalRecords = UiState.Loading) }

            try {
                val res = healthJournalRepository.getHealthJournals()
                _healthJournalState.update { it.copy(healthJournalRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _healthJournalState.update { it.copy(healthJournalRecords = UiState.Error(e.message.toString())) }
            }
        }

    private fun deleteHealthJournal(id: Int) =
        viewModelScope.launch(dispatcher) {
            changeDeletingStatus(UiState.Loading)

            val res = healthJournalRepository.deleteHealthJournal(id)

            changeDeletingStatus(if (res) UiState.Success(true) else UiState.Error("Fail to delete"))

            if (res)
                _healthJournalState.update {
                    it.copy(
                        healthJournalRecords = UiState.Success(
                            it.healthJournalRecords.getValue()
                                .filter { item -> item.id != id }
                        )
                    )
                }
        }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _healthJournalState.update { it.copy(adding = status) }
    }

    private fun changeEditingStatus(status: UiState<Boolean>) {
        _healthJournalState.update { it.copy(editing = status) }
    }

    private fun changeDeletingStatus(status: UiState<Boolean>) {
        _healthJournalState.update { it.copy(deleting = status) }
    }

    private fun setHealthJournalStateForTesting(state: HealthJournalState) {
        _healthJournalState.update { state }
    }
}