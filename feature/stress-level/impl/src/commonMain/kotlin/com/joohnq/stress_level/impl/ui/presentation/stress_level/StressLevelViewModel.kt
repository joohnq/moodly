package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetStressLevelsUseCase
import com.joohnq.stress_level.impl.ui.mapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.getValueOrEmpty
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toResultResource
import com.joohnq.ui.mapper.toUiState
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
    private val deleteStressLevelUseCase: DeleteStressLevelUseCase,
    initialState: StressLevelContract.State = StressLevelContract.State(),
) : BaseViewModel<StressLevelContract.State, StressLevelContract.Intent, StressLevelContract.SideEffect>(
    initialState = initialState
), StressLevelContract.ViewModel {

    override fun onIntent(intent: StressLevelContract.Intent) {
        when (intent) {
            StressLevelContract.Intent.GetAll -> getAll()
            is StressLevelContract.Intent.Add -> add(intent.record)
            is StressLevelContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            val res = deleteStressLevelUseCase(id).toUiState()

            res.onSuccess {
                emitEffect(StressLevelContract.SideEffect.Deleted)
                updateState {
                    it.copy(
                        UiState.Success(
                            state.value.records.getValueOrEmpty()
                                .filter { item -> item.id != id }
                        )
                    )
                }
            }.onFailure {
                emitEffect(StressLevelContract.SideEffect.ShowError(it))
            }
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }

            val res = getStressLevelsUseCase()
                .toResultResource { it.toResource() }
                .toUiState()

            updateState { it.copy(res) }
        }
    }

    private fun add(record: StressLevelRecord) {
        viewModelScope.launch {
            val res = addStressLevelUseCase(record).toUiState()

            res.onSuccess {
                emitEffect(StressLevelContract.SideEffect.Added)
            }.onFailure {
                emitEffect(StressLevelContract.SideEffect.ShowError(it))
            }
        }
    }
}