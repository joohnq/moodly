package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import kotlinx.coroutines.launch

class SelfJournalOverviewViewModel(
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    initialState: SelfJournalOverviewContract.State = SelfJournalOverviewContract.State(),
) : BaseViewModel<SelfJournalOverviewContract.State, SelfJournalOverviewContract.Intent, SelfJournalOverviewContract.SideEffect>(
        initialState = initialState
    ),
    SelfJournalOverviewContract.ViewModel {
    override fun onIntent(intent: SelfJournalOverviewContract.Intent) {
        when (intent) {
            is SelfJournalOverviewContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            try {
                val res =
                    getSelfJournalsUseCase()
                        .toResultResource { it.toResource() }
                        .toUiState()

                updateState { it.copy(res) }
            } catch (e: Exception) {
                emitEffect(SelfJournalOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteSelfJournalsUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        UiState.Success(
                            state.value.records
                                .getValueOrEmpty()
                                .filter { item -> item.id != id }
                        )
                    )
                }
            } catch (e: Exception) {
                emitEffect(SelfJournalOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
