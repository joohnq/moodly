package com.joohnq.sleep_quality.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SleepQualityOverviewViewModel(
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityOverviewContract.State = SleepQualityOverviewContract.State(),
) : BaseViewModel<SleepQualityOverviewContract.State, SleepQualityOverviewContract.Intent, SleepQualityOverviewContract.SideEffect>(
        initialState = initialState
    ),
    SleepQualityOverviewContract.ViewModel {
    override fun onIntent(intent: SleepQualityOverviewContract.Intent) {
        when (intent) {
            is SleepQualityOverviewContract.Intent.Delete -> delete(intent.id)
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
                emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(e.message.toString()))
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
                emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
