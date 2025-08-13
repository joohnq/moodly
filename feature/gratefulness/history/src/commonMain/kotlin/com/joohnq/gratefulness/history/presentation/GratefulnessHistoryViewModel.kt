package com.joohnq.gratefulness.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.gratefulness.api.use_case.DeleteGratefulnessUseCase
import com.joohnq.gratefulness.api.use_case.GetGratefulnessUseCase
import com.joohnq.gratefulness.history.presentation.GratefulnessHistoryContract.Intent
import com.joohnq.gratefulness.history.presentation.GratefulnessHistoryContract.SideEffect
import com.joohnq.gratefulness.history.presentation.GratefulnessHistoryContract.State
import com.joohnq.gratefulness.history.presentation.GratefulnessHistoryContract.ViewModel
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GratefulnessHistoryViewModel(
    private val getGratefulnessUseCase: GetGratefulnessUseCase,
    private val deleteGratefulnessUseCase: DeleteGratefulnessUseCase,
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
        getGratefulnessUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items,
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteGratefulnessUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            it.items.filter { mood ->
                                mood.id != id
                            }
                    )
                }
            } catch (e: Exception) {
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
