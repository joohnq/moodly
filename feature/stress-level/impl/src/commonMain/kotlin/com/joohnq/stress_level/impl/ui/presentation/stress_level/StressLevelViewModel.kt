package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetStressLevelsUseCase
import com.joohnq.stress_level.impl.ui.mapper.toResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.getValueOrEmpty
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toResultResource
import com.joohnq.ui.mapper.toUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
    private val deleteStressLevelUseCase: DeleteStressLevelUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StressLevelContract.State())
    val state: StateFlow<StressLevelContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<StressLevelContract.SideEffect>(Channel.Factory.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(intent: StressLevelContract.Intent) {
        when (intent) {
            StressLevelContract.Intent.GetAll -> getStressLevelRecords()
            is StressLevelContract.Intent.Add -> addStressLevelRecord(intent.record)
            is StressLevelContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            val res = deleteStressLevelUseCase(id).toUiState()
            res.onSuccess {
                _sideEffect.trySend(StressLevelContract.SideEffect.StressLevelDeleted)
                changeRecordsStatus(
                    UiState.Success(
                        state.value.records.getValueOrEmpty()
                            .filter { item -> item.id != id }
                    )
                )
            }.onFailure {
                _sideEffect.trySend(StressLevelContract.SideEffect.ShowError(it))
            }
        }
    }

    private fun getStressLevelRecords() =
        viewModelScope.launch {
            changeRecordsStatus(UiState.Loading)
            val res = getStressLevelsUseCase()
                .toResultResource { it.toResource() }
                .toUiState()
            changeRecordsStatus(res)
        }

    private fun addStressLevelRecord(stressLevelRecord: StressLevelRecord) =
        viewModelScope.launch {
            val res = addStressLevelUseCase(stressLevelRecord).toUiState()
            res.onSuccess {
                _sideEffect.trySend(StressLevelContract.SideEffect.StressLevelAdded)
            }.onFailure {
                _sideEffect.trySend(StressLevelContract.SideEffect.ShowError(it))
            }
        }

    private fun changeRecordsStatus(status: UiState<List<StressLevelRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}