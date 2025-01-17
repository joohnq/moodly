package com.joohnq.health_journal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.getValueOrNull
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _sideEffect = Channel<HealthJournalSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: HealthJournalIntent) {
        when (intent) {
            is HealthJournalIntent.GetHealthJournals -> getHealthJournals()
            is HealthJournalIntent.AddHealthJournal -> addHealthJournal(intent.healthJournalRecord)
            is HealthJournalIntent.DeleteHealthJournal -> deleteHealthJournal(intent.id)
            is HealthJournalIntent.UpdateHealthJournal -> updateHealthJournal(intent.healthJournalRecord)
        }
    }

    private fun addHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ) = viewModelScope.launch {
        val res = addHealthJournalsUseCase(healthJournalRecord).toUiState()
        res.onSuccess {
            _sideEffect.send(HealthJournalSideEffect.HealthJournalAdded)
        }.onFailure {
            _sideEffect.send(HealthJournalSideEffect.HealthJournalAdded)
        }
    }

    private fun updateHealthJournal(
        healthJournalRecord: HealthJournalRecord,
    ) = viewModelScope.launch {
        val res = updateHealthJournalsUseCase(healthJournalRecord).toUiState()
        res.onSuccess {
            _sideEffect.send(HealthJournalSideEffect.HealthJournalEdited)
        }.onFailure {
            _sideEffect.send(HealthJournalSideEffect.ShowError(it))
        }
    }

    private fun getHealthJournals() =
        viewModelScope.launch {
            changeHealthJournalRecordsStatus(UiState.Loading)
            val res = getHealthJournalsUseCase().toUiState()
            changeHealthJournalRecordsStatus(res)
        }

    private fun deleteHealthJournal(id: Int) =
        viewModelScope.launch {
            val res = deleteHealthJournalsUseCase(id).toUiState()
            res.onSuccess {
                changeHealthJournalRecordsStatus(
                    UiState.Success(
                        state.value.healthJournalRecords.getValueOrNull()
                            .filter { item -> item.id != id }
                    )
                )
                _sideEffect.send(HealthJournalSideEffect.HealthJournalDeleted)
            }.onFailure {
                _sideEffect.send(HealthJournalSideEffect.ShowError(it))
            }
        }

    private fun changeHealthJournalRecordsStatus(status: UiState<List<HealthJournalRecord>>) {
        _state.update { it.copy(healthJournalRecords = status) }
    }
}