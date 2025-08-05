package com.joohnq.stress_level.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
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
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            try {
                val res =
                    getAllStressLevelUseCase()
                        .toResultResource { it.toResource() }
                        .toUiState()

                updateState { it.copy(res) }
            } catch (e: Exception) {
                emitEffect(StressLevelHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteStressLevelUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        records =
                            UiState.Success(
                                state.value.records
                                    .getValueOrEmpty()
                                    .filter { item -> item.id != id }
                            )
                    )
                }
            } catch (e: Exception) {
                emitEffect(StressLevelHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
