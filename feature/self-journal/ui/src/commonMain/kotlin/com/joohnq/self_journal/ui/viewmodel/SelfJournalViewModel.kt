package com.joohnq.self_journal.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.*
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.UpdateSelfJournalsUseCase
import com.joohnq.self_journal.ui.mapper.toResource
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SelfJournalViewModel(
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    private val updateSelfJournalsUseCase: UpdateSelfJournalsUseCase,
    private val addSelfJournalsUseCase: AddSelfJournalsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SelfJournalState())
    val state: StateFlow<SelfJournalState> = _state.asStateFlow()

    private val _sideEffect = Channel<SelfJournalSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: SelfJournalIntent) {
        when (intent) {
            is SelfJournalIntent.GetAll -> getSelfJournals()
            is SelfJournalIntent.Add -> addSelfJournal(intent.record)
            is SelfJournalIntent.Delete -> deleteSelfJournal(intent.id)
            is SelfJournalIntent.Update -> updateSelfJournal(intent.record)
        }
    }

    private fun addSelfJournal(
        record: SelfJournalRecord,
    ) = viewModelScope.launch {
        val res = addSelfJournalsUseCase(record).toUiState()
        res.onSuccess {
            _sideEffect.send(SelfJournalSideEffect.SelfJournalAdded)
        }.onFailure {
            _sideEffect.send(SelfJournalSideEffect.SelfJournalAdded)
        }
    }

    private fun updateSelfJournal(
        record: SelfJournalRecord,
    ) = viewModelScope.launch {
        val res = updateSelfJournalsUseCase(record).toUiState()
        res.onSuccess {
            _sideEffect.send(SelfJournalSideEffect.SelfJournalEdited)
        }.onFailure {
            _sideEffect.send(SelfJournalSideEffect.ShowError(it))
        }
    }

    private fun getSelfJournals() =
        viewModelScope.launch {
            changeRecordsStatus(UiState.Loading)
            val res = getSelfJournalsUseCase()
                .toResultResource { it.toResource() }
                .toUiState()
            changeRecordsStatus(res)
        }

    private fun deleteSelfJournal(id: Int) =
        viewModelScope.launch {
            val res = deleteSelfJournalsUseCase(id).toUiState()
            res.onSuccess {
                changeRecordsStatus(
                    UiState.Success(
                        state.value.records.getValueOrNull()
                            .filter { item -> item.id != id }
                    )
                )
                _sideEffect.send(SelfJournalSideEffect.SelfJournalDeleted)
            }.onFailure {
                _sideEffect.send(SelfJournalSideEffect.ShowError(it))
            }
        }

    private fun changeRecordsStatus(status: UiState<List<SelfJournalRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}