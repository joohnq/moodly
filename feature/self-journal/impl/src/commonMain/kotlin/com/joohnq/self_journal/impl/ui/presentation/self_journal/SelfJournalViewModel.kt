package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.api.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.UpdateSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class SelfJournalViewModel(
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    private val updateSelfJournalsUseCase: UpdateSelfJournalsUseCase,
    private val addSelfJournalsUseCase: AddSelfJournalsUseCase,
    initialState: SelfJournalContract.State = SelfJournalContract.State(),
) : BaseViewModel<SelfJournalContract.State, SelfJournalContract.Intent, SelfJournalContract.SideEffect>(
        initialState = initialState
    ),
    SelfJournalContract.ViewModel {
    override fun onIntent(intent: SelfJournalContract.Intent) {
        when (intent) {
            is SelfJournalContract.Intent.GetAll -> getAll()
            is SelfJournalContract.Intent.Add -> add(intent.record)
            is SelfJournalContract.Intent.Delete -> delete(intent.id)
            is SelfJournalContract.Intent.Update -> update(intent.record)
        }
    }

    private fun add(record: SelfJournalRecord) =
        viewModelScope.launch {
            val res = addSelfJournalsUseCase(record).toUiState()
            res
                .onSuccess {
                    emitEffect(SelfJournalContract.SideEffect.SelfJournalAdded)
                }.onFailure {
                    emitEffect(SelfJournalContract.SideEffect.ShowError(it))
                }
        }

    private fun update(record: SelfJournalRecord) =
        viewModelScope.launch {
            val res = updateSelfJournalsUseCase(record).toUiState()
            res
                .onSuccess {
                    emitEffect(SelfJournalContract.SideEffect.Updated)
                }.onFailure {
                    emitEffect(SelfJournalContract.SideEffect.ShowError(it))
                }
        }

    private fun getAll() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            val res =
                getSelfJournalsUseCase()
                    .toResultResource { it.toResource() }
                    .toUiState()
            updateState { it.copy(res) }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteSelfJournalsUseCase(id).toUiState()
            res
                .onSuccess {
                    updateState {
                        it.copy(
                            UiState.Success(
                                state.value.records
                                    .getValueOrEmpty()
                                    .filter { item -> item.id != id }
                            )
                        )
                    }
                    emitEffect(SelfJournalContract.SideEffect.SelfJournalDeleted)
                }.onFailure {
                    emitEffect(SelfJournalContract.SideEffect.ShowError(it))
                }
        }
}
