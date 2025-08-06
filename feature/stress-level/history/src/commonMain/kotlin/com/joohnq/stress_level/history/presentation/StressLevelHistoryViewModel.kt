package com.joohnq.stress_level.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.api.filterBy
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toGroupedByDate
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class StressLevelHistoryViewModel(
    private val getAllStressLevelUseCase: GetAllStressLevelUseCase,
    private val deleteStressLevelUseCase: DeleteStressLevelUseCase,
    initialState: StressLevelHistoryContract.State = StressLevelHistoryContract.State(),
) : BaseViewModel<StressLevelHistoryContract.State, StressLevelHistoryContract.Intent, StressLevelHistoryContract.SideEffect>(
        initialState = initialState
    ),
    StressLevelHistoryContract.ViewModel {
    override fun onIntent(intent: StressLevelHistoryContract.Intent) {
        when (intent) {
            is StressLevelHistoryContract.Intent.Delete -> delete(intent.id)
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
                        items = items.toResource().toGroupedByDate()
                    )
                }
            }.catch { e ->
                emitEffect(StressLevelHistoryContract.SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteStressLevelUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            state.value.items
                                .filterBy { item -> item.id != id }
                    )
                }
            } catch (e: Exception) {
                emitEffect(StressLevelHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
