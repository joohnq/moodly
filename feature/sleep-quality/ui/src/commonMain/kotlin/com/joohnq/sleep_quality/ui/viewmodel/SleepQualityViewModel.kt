package com.joohnq.sleep_quality.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.toUiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SleepQualityState())
    val state: StateFlow<SleepQualityState> = _state.asStateFlow()

    fun onAction(intent: SleepQualityIntent) {
        when (intent) {
            SleepQualityIntent.GetSleepQualityRecords -> getSleepQualityRecords()
            is SleepQualityIntent.AddSleepQualityRecord ->
                addSleepQualityRecord(intent.sleepQualityRecord)

            SleepQualityIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
        }
    }

    private fun getSleepQualityRecords() {
        viewModelScope.launch {
            changeSleepQualityRecordsStatus(UiState.Loading)
            val res = getSleepQualitiesUseCase().toUiState()
            changeSleepQualityRecordsStatus(res)
        }
    }

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            changeAddingStatus(UiState.Loading)
            val res = addSleepQualityUseCase(sleepQualityRecord).toUiState()
            changeAddingStatus(res)
        }

    private fun changeSleepQualityRecordsStatus(status: UiState<List<SleepQualityRecord>>) {
        _state.update { it.copy(sleepQualityRecords = status) }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _state.update { it.copy(adding = status) }
    }
}