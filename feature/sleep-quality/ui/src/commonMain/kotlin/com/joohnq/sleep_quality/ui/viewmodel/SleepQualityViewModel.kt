package com.joohnq.sleep_quality.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.shared.ui.state.UiState
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
    private val _sleepQualityState = MutableStateFlow(SleepQualityState())
    val sleepQualityState: StateFlow<SleepQualityState> = _sleepQualityState.asStateFlow()

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
            _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Loading) }
            try {
                val res = getSleepQualitiesUseCase()
                _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Error(e.message.toString())) }
            }
        }
    }

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            changeAddingStatus(UiState.Loading)
            val res = addSleepQualityUseCase(sleepQualityRecord)
            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _sleepQualityState.update { it.copy(adding = status) }
    }
}