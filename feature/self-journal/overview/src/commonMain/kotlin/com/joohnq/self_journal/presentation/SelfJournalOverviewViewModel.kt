package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }

        getSelfJournalsUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items.toResource(),
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(
                    SelfJournalOverviewContract.SideEffect.ShowError(e.message.toString())
                )
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
                                .filter { item -> item.id != id }
                    )
                }
            } catch (e: Exception) {
                emitEffect(SelfJournalOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
