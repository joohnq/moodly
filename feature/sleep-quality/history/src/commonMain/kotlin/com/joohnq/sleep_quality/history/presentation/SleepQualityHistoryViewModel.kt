package com.joohnq.sleep_quality.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContract.Intent
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContract.SideEffect
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContract.State
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryContract.ViewModel
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SleepQualityHistoryViewModel(
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
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
        getSleepQualitiesUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items.toResource(),
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Long) {
        viewModelScope.launch {
            try {
                deleteSleepQualityUseCase(id).getOrThrow()

                updateState {
                    it.copy(
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
