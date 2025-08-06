package com.joohnq.sleep_quality.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SleepQualityHistoryViewModel(
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityHistoryContract.State = SleepQualityHistoryContract.State(),
) : BaseViewModel<SleepQualityHistoryContract.State, SleepQualityHistoryContract.Intent, SleepQualityHistoryContract.SideEffect>(
    initialState = initialState
),
    SleepQualityHistoryContract.ViewModel {
    override fun onIntent(intent: SleepQualityHistoryContract.Intent) {
        when (intent) {
            is SleepQualityHistoryContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }
        getSleepQualitiesUseCase()
            .onEach { items ->
                val resources = items.toResource()
                updateState {
                    it.copy(
                        items = resources,
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(SleepQualityHistoryContract.SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
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
                emitEffect(SleepQualityHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}