package com.joohnq.stress_level.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.onFailure
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.toResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val getStressLevelsUseCase: GetStressLevelsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StressLevelState())
    val state: StateFlow<StressLevelState> = _state.asStateFlow()

    private val _sideEffect = Channel<StressLevelSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: StressLevelIntent) {
        when (intent) {
            StressLevelIntent.GetStressLevelRecords -> getStressLevelRecords()
            is StressLevelIntent.AddStressLevelRecord -> addStressLevelRecord(intent.stressLevelRecord)
        }
    }

    private fun getStressLevelRecords() =
        viewModelScope.launch {
            changeRecordsStatus(UiState.Loading)
            val res = getStressLevelsUseCase()
                .toResource().toUiState()
            changeRecordsStatus(res)
        }

    private fun addStressLevelRecord(stressLevelRecord: StressLevelRecord) =
        viewModelScope.launch {
            val res = addStressLevelUseCase(stressLevelRecord).toUiState()
            res.onSuccess {
                _sideEffect.trySend(StressLevelSideEffect.StressLevelAdded)
            }.onFailure {
                _sideEffect.trySend(StressLevelSideEffect.ShowError(it))
            }
        }

    private fun changeRecordsStatus(status: UiState<List<StressLevelRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}