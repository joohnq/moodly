package com.joohnq.stress_level.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetAllStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class StressLevelOverviewViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getAllStressLevelUseCase: GetAllStressLevelUseCase,
    private val deleteStressLevelUseCase: DeleteStressLevelUseCase,
    initialState: StressLevelOverviewContract.State = StressLevelOverviewContract.State(),
) : BaseViewModel<StressLevelOverviewContract.State, StressLevelOverviewContract.Intent, StressLevelOverviewContract.SideEffect>(
        initialState = initialState
    ),
    StressLevelOverviewContract.ViewModel {
    override fun onIntent(intent: StressLevelOverviewContract.Intent) {
        when (intent) {
            StressLevelOverviewContract.Intent.GetAll -> getAll()
            is StressLevelOverviewContract.Intent.Add -> add(intent.record)
            is StressLevelOverviewContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            val res = deleteStressLevelUseCase(id).toUiState()

            res
                .onSuccess {
                    emitEffect(StressLevelOverviewContract.SideEffect.Deleted)
                    updateState {
                        it.copy(
                            UiState.Success(
                                state.value.records
                                    .getValueOrEmpty()
                                    .filter { item -> item.id != id }
                            )
                        )
                    }
                }.onFailure {
                    emitEffect(StressLevelOverviewContract.SideEffect.ShowError(it))
                }
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }

            val res =
                getAllStressLevelUseCase()
                    .toResultResource { it.toResource() }
                    .toUiState()

            updateState { it.copy(res) }
        }
    }

    private fun add(record: StressLevelRecord) {
        viewModelScope.launch {
            val res = addStressLevelUseCase(record).toUiState()

            res
                .onSuccess {
                    emitEffect(StressLevelOverviewContract.SideEffect.Added)
                }.onFailure {
                    emitEffect(StressLevelOverviewContract.SideEffect.ShowError(it))
                }
        }
    }
}
