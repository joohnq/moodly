package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.api.filterBy
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toGroupedByDate
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SelfJournalHistoryViewModel(
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    initialState: SelfJournalHistoryContract.State = SelfJournalHistoryContract.State(),
) : BaseViewModel<
        SelfJournalHistoryContract.State,
        SelfJournalHistoryContract.Intent,
        SelfJournalHistoryContract.SideEffect
    >(
        initialState = initialState
    ),
    SelfJournalHistoryContract.ViewModel {
    override fun onIntent(intent: SelfJournalHistoryContract.Intent) {
        when (intent) {
            is SelfJournalHistoryContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }
        getSelfJournalsUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items.toResource().toGroupedByDate(),
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(SelfJournalHistoryContract.SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteSelfJournalsUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            state.value.items
                                .filterBy { item -> item.id != id }
                    )
                }
            } catch (e: Exception) {
                emitEffect(SelfJournalHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
