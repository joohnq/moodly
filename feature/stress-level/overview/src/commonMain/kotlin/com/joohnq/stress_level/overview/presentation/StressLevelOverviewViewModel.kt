package com.joohnq.stress_level.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContract.Intent
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContract.SideEffect
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContract.State
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewContract.ViewModel
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StressLevelOverviewViewModel(
    private val getAllStressLevelUseCase: GetAllStressLevelUseCase,
    private val deleteStressLevelUseCase: DeleteStressLevelUseCase,
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

        getAllStressLevelUseCase()
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
                deleteStressLevelUseCase(id).getOrThrow()

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
