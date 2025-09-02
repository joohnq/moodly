package com.joohnq.self_journal.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.self_journal.api.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.api.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toResource
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract.Intent
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract.SideEffect
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract.State
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract.ViewModel
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SelfJournalOverviewViewModel(
    private val getSelfJournalsUseCase: GetSelfJournalsUseCase,
    private val deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase,
    initialState: State = State(),
) : BaseViewModel<State, Intent, SideEffect>(
        initialState = initialState
    ),
    ViewModel {
    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Delete -> delete(intent.id)
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
                    SideEffect.ShowError(e.message.toString())
                )
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Long) {
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
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
